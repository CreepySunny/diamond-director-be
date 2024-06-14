package nl.fontys.s3.indi.diamond_director_be.Controllers;


import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.fontys.s3.indi.diamond_director_be.business.AssignCoachToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.CreateTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.FindTeamFromUserEmailUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.AssignCoachTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.FindTeamResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
@RequestMapping("/team")
public class TeamController {
    private final CreateTeamUseCase createTeamUseCase;
    private final FindTeamFromUserEmailUseCase findTeamFromUserEmailUseCase;
    private final AssignCoachToTeamUseCase assignCoachToTeamUseCase;

    @PostMapping
    @RolesAllowed({"COACH"})
    public ResponseEntity<Long> createNewTeam(@RequestBody @Valid CreateTeamRequest request){
           Long newId = createTeamUseCase.createTeam(request);
           return ResponseEntity.status(HttpStatus.CREATED).body(newId);
    }

    @GetMapping("{email}")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Team> findTeamByUserEmail(@PathVariable String email){
        Team team = findTeamFromUserEmailUseCase.findTeamFromUserEmail(email);
        return ResponseEntity.ok(team);
    }

    @PutMapping
    @RolesAllowed({"COACH"})
    public ResponseEntity<Void> assignCoachToTeam(@RequestBody @Valid AssignCoachTeamRequest request){
            assignCoachToTeamUseCase.assignCoachToTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
