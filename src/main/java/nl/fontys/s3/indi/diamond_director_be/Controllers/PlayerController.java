package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CalculateBattingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_PLAYER_EXCEPTION;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayRepository playRepository;
    private CreatePlayerUseCase createPlayerUseCase;
    private final CalculateBattingStatisticsUseCase calculateBattingStatisticsUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createPlayer(@RequestBody @Valid CreatePlayerRequest request) throws ParseException {
        CreateUserResponse saved = createPlayerUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        Player foundPlayer;
        try {
            foundPlayer = PlayerConverter.convert(playerRepository.findById(id).orElseThrow(NO_PLAYER_EXCEPTION::new));
        }catch (NO_PLAYER_EXCEPTION ex){
            return ResponseEntity.status(ex.getStatusCode()).build();
        }

        return ResponseEntity.ok(foundPlayer);
    }

    @GetMapping("/{id}/batting")
    public ResponseEntity<BattingStatistics> getBattingStatsByPlayerId(@PathVariable Long id){
        List<PlayEntity> foundPlays = playRepository.findByBatterId(findPlayerById(id).getId());
        List<Play> plays = foundPlays.stream().map(PlayConverter::convert).toList();

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(plays);

        return ResponseEntity.ok(battingStatistics);
    }

    @GetMapping("/{id}/pitching")
    public ResponseEntity<BattingStatistics> getPitchingStatsByPlayerId(@PathVariable Long id){
        List<PlayEntity> foundPlays = playRepository.findByBatterId(findPlayerById(id).getId());
        List<Play> plays = foundPlays.stream().map(PlayConverter::convert).toList();

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(plays);

        return ResponseEntity.ok(battingStatistics);
    }

    private PlayerEntity findPlayerById(Long playerId){
        Optional<PlayerEntity> foundPlayer = playerRepository.findById(playerId);
        return foundPlayer.orElseThrow(NO_PLAYER_EXCEPTION::new);
    }
}
