package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ScoreController {


    @PostMapping
    public ResponseEntity<Void> addNewGameScore(@RequestBody @Valid ScoreRequest scoreRequest) {

        return ResponseEntity.ok().build();
    }
}
