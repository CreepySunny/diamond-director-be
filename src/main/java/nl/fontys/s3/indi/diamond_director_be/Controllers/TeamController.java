package nl.fontys.s3.indi.diamond_director_be.Controllers;


import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.AssignCoachToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.AssignPlayerToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.*;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4173", "http://localhost:3000"})
@RequestMapping("/team")
public class TeamController {
    private final CreateTeamUseCase createTeamUseCase;
    private final FindTeamFromUserEmailUseCase findTeamFromUserEmailUseCase;
    private final AssignCoachToTeamUseCase assignCoachToTeamUseCase;
    private final AssignPlayerToTeamUseCase assignPlayerToTeamUseCase;
    private final FindAllTeamsUseCase findAllTeamsUseCase;
    private final DeleteTeamUsingTeamNameUseCase deleteTeamUsingTeamNameUseCase;
    private final SearchForTeamUsingTeamNameUseCase searchForTeamUsingTeamNameUseCase;

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

    @PutMapping("assign/coach")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Void> assignCoachTeam(@RequestBody @Valid AssignCoachTeamRequest request){
            assignCoachToTeamUseCase.assignCoachToTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("assign/player")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Void> assignPlayerTeam(@RequestBody @Valid AssignPlayerTeamRequest request){
        assignPlayerToTeamUseCase.assignPlayerToTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @RolesAllowed({"COACH"})
    public ResponseEntity<Team[]> findAllTeams(){
        List<Team> foundTeams = findAllTeamsUseCase.findAllTeams();
        return ResponseEntity.ok(foundTeams.toArray(new Team[foundTeams.size()]));
    }

    @DeleteMapping("{teamName}")
    @RolesAllowed({"COACH"})
    public ResponseEntity<Void> deleteTeamByName(@PathVariable String teamName){
        deleteTeamUsingTeamNameUseCase.deleteTeamUsingTeamName(teamName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("search/{teamName}")
    public ResponseEntity<List<Team>> searchForTeamUsingTeamName(@PathVariable String teamName){
        return ResponseEntity.ok(searchForTeamUsingTeamNameUseCase.searchForTeam(teamName));
    }
}
