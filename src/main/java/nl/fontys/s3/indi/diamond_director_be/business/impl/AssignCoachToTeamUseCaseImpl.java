package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.AssignCoachToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.AssignCoachTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignCoachToTeamUseCaseImpl implements AssignCoachToTeamUseCase {
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;

    @Override
    public void assignCoachToTeam(AssignCoachTeamRequest request) {
        CoachEntity foundCoach = coachRepository.findByUserEntityEmail(request.getCoachEmail()).orElseThrow(NO_COACH_EXCEPTION::new);
        TeamEntity foundTeam = teamRepository.findByTeamName(request.getTeamName()).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        List<CoachEntity> currentCoaches = foundTeam.getCoaches();
        currentCoaches.add(foundCoach);
        foundTeam.setCoaches(currentCoaches);
        teamRepository.save(foundTeam);
    }
}
