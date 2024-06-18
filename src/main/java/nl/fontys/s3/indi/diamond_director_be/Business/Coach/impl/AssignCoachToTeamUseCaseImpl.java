package nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.AssignCoachToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignCoachTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssignCoachToTeamUseCaseImpl implements AssignCoachToTeamUseCase {
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;

    @Override
    @Transactional
    public void assignCoachToTeam(AssignCoachTeamRequest request) {
        CoachEntity foundCoach = coachRepository.findById(request.getCoachId()).orElseThrow(NO_COACH_EXCEPTION::new);
        TeamEntity foundTeam = teamRepository.findByTeamName(request.getTeamName()).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        foundCoach.setTeam(foundTeam);
        coachRepository.save(foundCoach);
    }
}
