package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CreateCoachRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/coach")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4173"})
public class CoachController {
    private final CreateCoachUseCase createCoachUseCase;
    private final FindCoachesFromTeamNameUseCase findCoachesFromTeamNameUseCase;
    private final FindCoachesNoTeamUseCase findCoachesNoTeamUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createCoach(@RequestBody @Valid CreateCoachRequest request) {
        CreateUserResponse saved = createCoachUseCase.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/team/{teamName}")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Coaches[]> findCoachesFromTeamName(@PathVariable String teamName){
        List<Coaches> foundCoaches = findCoachesFromTeamNameUseCase.findCoachFromTeamName(teamName);
        return ResponseEntity.ok(foundCoaches.toArray(new Coaches[foundCoaches.size()]));
    }

    @GetMapping("/no-team")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Coaches[]> findCoachesNoTeam(){
        List<Coaches> foundCoaches = findCoachesNoTeamUseCase.findCoachesWithNoTeam();
        return ResponseEntity.ok(foundCoaches.toArray(new Coaches[foundCoaches.size()]));
    }
}