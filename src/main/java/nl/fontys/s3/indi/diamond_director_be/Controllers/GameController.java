package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.*;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.*;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class GameController {
    private final CreateGameUseCase createGameUseCase;
    private final GameRepository gameRepository;
    private final GetGamesByCoachIdUseCse getGamesByCoachIdUseCse;

    @PostMapping
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<Long> createGame(@RequestBody CreateGameRequest request) {
        return ResponseEntity.ok(createGameUseCase.createGame(request));
    }

    @GetMapping("{id}")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<GameResponse> getGame(@PathVariable Long id) {
        GameEntity foundgame = gameRepository.findById(id).orElseThrow(NO_GAME_EXCEPTION::new);

        GameResponse response = GameResponse.builder()
                .season(foundgame.getSeason())
                .gameId(foundgame.getId())
                .awayTeamName(foundgame.getAwayTeam().getTeamName())
                .homeTeamName(foundgame.getHomeTeam().getTeamName())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<GameEntity> foundgames = gameRepository.findAll();

        List<GameResponse> responses;

        if (foundgames.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        responses = foundgames.stream().map((entity) -> GameResponse.builder()
                .season(entity.getSeason())
                .gameId(entity.getId())
                .awayTeamName(entity.getAwayTeam().getTeamName())
                .homeTeamName(entity.getHomeTeam().getTeamName())
                .build()).toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("{id}/all")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<List<GameResponse>> getAllGamesByCoachId(@PathVariable Long id) {

        List<GameResponse> responses = getGamesByCoachIdUseCse.findGamesByCoachId(id);

        return ResponseEntity.ok(responses);
    }
}
