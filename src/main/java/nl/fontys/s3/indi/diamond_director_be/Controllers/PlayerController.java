package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CalculateBattingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindAllPlayerFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.FindPlayerByUserIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.impl.FindAllPlayersNoTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.BattingStatistics;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayRepository playRepository;
    private final FindAllPlayersNoTeamUseCaseImpl findAllPlayersNoTeamUseCaseImpl;
    private final FindAllPlayerFromTeamNameUseCase findPlayersFromTeamName;
    private CreatePlayerUseCase createPlayerUseCase;
    private final CalculateBattingStatisticsUseCase calculateBattingStatisticsUseCase;
    private final FindPlayerByUserIdUseCase findPlayerByUserIdUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createPlayer(@RequestBody @Valid CreatePlayerRequest request) throws ParseException {
        CreateUserResponse saved = createPlayerUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("{id}")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        Player foundPlayer = findPlayerByUserIdUseCase.findPlayerByUserId(id);
        return ResponseEntity.ok(foundPlayer);
    }

    @GetMapping("no-team")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<List<Player>> getAllPlayerWithNoTeam(){
        return ResponseEntity.ok(findAllPlayersNoTeamUseCaseImpl.findPlayersNoTeam());
    }


    @GetMapping("team/{teamName}")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<List<Player>> getPlayersFromTeamName(@PathVariable @Valid String teamName){
        return ResponseEntity.ok(findPlayersFromTeamName.findPlayersFromTeamName(teamName));
    }


    @GetMapping("/{id}/batting")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<BattingStatistics> getBattingStatsByPlayerId(@PathVariable Long id){
        List<PlayEntity> foundPlays = playRepository.findByBatter(findPlayerById(id));
        List<Play> plays = foundPlays.stream().map(PlayConverter::convert).toList();

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(plays);

        return ResponseEntity.ok(battingStatistics);
    }

    @GetMapping("/{id}/pitching")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<BattingStatistics> getPitchingStatsByPlayerId(@PathVariable Long id){
        List<PlayEntity> foundPlays = playRepository.findByPitcher(findPlayerById(id));
        List<Play> plays = foundPlays.stream().map(PlayConverter::convert).toList();

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(plays);

        return ResponseEntity.ok(battingStatistics);
    }

    private PlayerEntity findPlayerById(Long playerId){
        return playerRepository.findById(playerId).orElseThrow(NO_PLAYER_EXCEPTION::new);
    }
}
