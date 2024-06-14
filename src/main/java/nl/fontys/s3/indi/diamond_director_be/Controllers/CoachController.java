package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindCoachesFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.FindCoachesFromTeamNameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/coach")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class CoachController {
    private final CreateCoachUseCase createCoachUseCase;
    private final FindCoachesFromTeamNameUseCase findCoachesFromTeamNameUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createPlayer(@RequestBody @Valid CreateCoachRequest request) throws ParseException {
        CreateUserResponse saved = createCoachUseCase.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<FindCoachesFromTeamNameResponse> findCoachesFromTeamName(@PathVariable String teamName){
        List<Coaches> foundCoaches;
        try {
            foundCoaches = findCoachesFromTeamNameUseCase.findCoachFromTeamName(teamName);
        }catch (NO_COACH_EXCEPTION exception){
            return ResponseEntity.status(exception.getStatusCode()).body(FindCoachesFromTeamNameResponse.builder().exception(exception).build());
        }
        return ResponseEntity.ok(FindCoachesFromTeamNameResponse.builder().coaches(foundCoaches).build());
    }
}