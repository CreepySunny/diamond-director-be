package nl.fontys.s3.indi.diamond_director_be.Controllers;


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
    public ResponseEntity<Long> createNewTeam(@RequestBody @Valid CreateTeamRequest request){
           Long newId = createTeamUseCase.createTeam(request);

           return ResponseEntity.status(HttpStatus.CREATED).body(newId);
    }

    @GetMapping("{email}")
    public ResponseEntity<FindTeamResponse> findTeamByUserEmail(@PathVariable String email){
        Team team;
        try {
            team = findTeamFromUserEmailUseCase.findTeamFromUserEmail(email);
        }catch (HttpStatusCodeException exception){
            return ResponseEntity.status(exception.getStatusCode()).body(FindTeamResponse.builder().exception(exception).build());
        }
        return ResponseEntity.ok(FindTeamResponse.builder().team(team).build());
    }

    @PutMapping
    public ResponseEntity<Void> assignCoachToTeam(@RequestBody @Valid AssignCoachTeamRequest request){
        try {
            assignCoachToTeamUseCase.assignCoachToTeam(request);
        }catch (HttpStatusCodeException exception){
            return ResponseEntity.status(exception.getStatusCode()).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
