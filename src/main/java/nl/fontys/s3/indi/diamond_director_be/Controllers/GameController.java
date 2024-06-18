package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.*;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.GetAllPlayersFromGameIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.*;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
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
    private final GetGamesByCoachUserEmailUseCse getGamesByCoachUserEmailUseCse;
    private final FindGameFromGameIdUseCase findGameFromGameIdUseCase;
    private final GetAllPlayersFromGameIdUseCase getAllPlayersFromGameIdUseCase;
    private final AddNewBaseballPlayUseCase addNewBaseballPlayUseCase;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final FindAllGameUseCase findAllGameUseCase;

    @PostMapping
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<Long> createGame(@RequestBody CreateGameRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createGameUseCase.createGame(request));
    }

    @GetMapping("{id}")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<GameResponse> getGame(@PathVariable Long id) {
        Game foundGame = findGameFromGameIdUseCase.findGameFromGameId(id);
        GameResponse response = GameResponse.builder()
                .season(foundGame.getSeason())
                .gameId(foundGame.getId())
                .awayTeamName(foundGame.getAway().getTeamName())
                .homeTeamName(foundGame.getHome().getTeamName())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("games")
    public ResponseEntity<GameResponse[]> getAllGames() {
        List<Game> foundGames = findAllGameUseCase.findAll();

        List<GameResponse> responses = foundGames.stream()
                .map(game -> GameResponse.builder()
                        .gameId(game.getId())
                        .season(game.getSeason())
                        .homeTeamName(game.getHome().getTeamName())
                        .awayTeamName(game.getAway().getTeamName())
                        .build())
                .toList();

        return ResponseEntity.ok(responses.toArray(new GameResponse[foundGames.size()]));
    }

    @GetMapping("players/{gameId}")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<List<Player>> getPlayersFromGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(getAllPlayersFromGameIdUseCase.getAllPlayersFromGameId(gameId));
    }

    @GetMapping("{email}/all")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<List<GameResponse>> getAllGamesByCoachEmail(@PathVariable String email) {

        List<GameResponse> responses = getGamesByCoachUserEmailUseCse.findGamesByCoachUserEmail(email);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("score")
    @RolesAllowed({"ADMIN", "COACH"})
    public ResponseEntity<Void> addNewGameScore(@RequestBody @Valid ScoreRequest scoreRequest) {
        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        GameStateUpdateMessage message = GameStateUpdateMessage.builder()
                .awayScore(updatedGame.getAwayScore())
                .homeScore(updatedGame.getHomeScore())
                .awayTeamName(updatedGame.getAway().getTeamName())
                .homeTeamName(updatedGame.getHome().getTeamName())
                .outs(updatedGame.getOuts())
                .build();

        simpMessagingTemplate.convertAndSend("/game/" + updatedGame.getId(), message);

        return ResponseEntity.ok().build();
    }
}
