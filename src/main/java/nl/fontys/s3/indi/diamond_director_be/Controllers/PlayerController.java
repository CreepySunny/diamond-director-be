package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.*;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.FindAllPlayersNoTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.*;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4173"})
public class PlayerController {
    private final FindAllPlayersNoTeamUseCase findAllPlayersNoTeamUseCaseImpl;
    private final FindAllPlayerFromTeamNameUseCase findPlayersFromTeamName;
    private final CreatePlayerUseCase createPlayerUseCase;
    private final CalculateBattingStatisticsUseCase calculateBattingStatisticsUseCase;
    private final CalculatePitchingStatisticsUseCase calculatePitchingStatisticsUseCase;
    private final FindPlayerByUserIdUseCase findPlayerByUserIdUseCase;
    private final GetPercentageBatterToPlayerPositionUseCase getPercentageBatterToPlayerPositionUseCase;
    private final GetPercentagePitcherToPlayerPositionUseCase getPercentagePitcherToPlayerPositionUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createPlayer(@RequestBody @Valid CreatePlayerRequest request) throws ParseException {
        CreateUserResponse saved = createPlayerUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //BrokenAccessController
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
    public ResponseEntity<PitchingStatistics> getBattingStatsByPlayerId(@PathVariable Long id){

        PitchingStatistics battingStatistics = calculatePitchingStatisticsUseCase.calculatePitchingStatistics(id);

        return ResponseEntity.ok(battingStatistics);
    }

    @GetMapping("/{id}/pitching")
    @RolesAllowed({"COACH", "PLAYER"})
    public ResponseEntity<BattingStatistics> getPitchingStatsByPlayerId(@PathVariable Long id){

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(id);

        return ResponseEntity.ok(battingStatistics);
    }

    @GetMapping("/{playerUserId}/{position}/batting")
    @RolesAllowed({"PLAYER"})
    public ResponseEntity<PlayerPositionBatterStatisticsResponse> getFieldPositionSpecificCompletionAndStatsBatting(@PathVariable Long playerUserId, @PathVariable String position){
        PlayerPositionBatterStatisticsResponse response = getPercentageBatterToPlayerPositionUseCase.getPerPositionStats(playerUserId, PlayerPosition.valueOf(position));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playerUserId}/{position}/pitching")
    @RolesAllowed({"PLAYER"})
    public ResponseEntity<PlayerPositionPitcherStatisticsResponse> getFieldPositionSpecificCompletionAndStatsPitching(@PathVariable Long playerUserId, @PathVariable String position){
        PlayerPositionPitcherStatisticsResponse response = getPercentagePitcherToPlayerPositionUseCase.getPercentageForPitcher(playerUserId, PlayerPosition.valueOf(position));
        return ResponseEntity.ok(response);
    }
}
