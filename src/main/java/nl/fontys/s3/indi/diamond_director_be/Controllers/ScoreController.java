package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.AddNewBaseballPlayUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.GameStateUpdateMessage;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ScoreController {
    private final AddNewBaseballPlayUseCase addNewBaseballPlayUseCase;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public ResponseEntity<Void> addNewGameScore(@RequestBody @Valid ScoreRequest scoreRequest) {
        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        GameStateUpdateMessage message = GameStateUpdateMessage.builder()
                .awayScore(updatedGame.getAwayScore())
                .homeScore(updatedGame.getHomeScore())
                .awayTeamName(updatedGame.getAway().getTeamName())
                .homeTeamName(updatedGame.getHome().getTeamName())
                .outs(updatedGame.getOuts())
                .build();

        simpMessagingTemplate.convertAndSend("/topic/game/"+updatedGame.getId(), message);
        return ResponseEntity.ok().build();
    }
}
