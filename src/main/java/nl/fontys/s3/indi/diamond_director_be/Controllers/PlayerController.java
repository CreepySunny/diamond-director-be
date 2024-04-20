package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
//@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class PlayerController {
    private CreatePlayerUseCase createPlayerUseCase;

    @PostMapping()
    public ResponseEntity<CreatePlayerResponse> createPlayer(@RequestBody @Valid CreatePlayerRequest request) throws ParseException {
        CreatePlayerResponse saved = createPlayerUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
