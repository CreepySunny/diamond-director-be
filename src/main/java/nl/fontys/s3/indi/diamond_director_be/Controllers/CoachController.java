package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.CreateCoachRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@AllArgsConstructor
@RequestMapping("/coach")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class CoachController {
    private final CreateCoachUseCase createCoachUseCase;
    @PostMapping()
    public ResponseEntity<CreateUserResponse> createPlayer(@RequestBody @Valid CreateCoachRequest request) throws ParseException {
        CreateUserResponse saved = createCoachUseCase.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}