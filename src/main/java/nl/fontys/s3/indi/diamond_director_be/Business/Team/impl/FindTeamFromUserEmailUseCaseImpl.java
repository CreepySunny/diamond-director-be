package nl.fontys.s3.indi.diamond_director_be.Business.Team.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.FindTeamFromUserEmailUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTeamFromUserEmailUseCaseImpl implements FindTeamFromUserEmailUseCase {
    private TeamRepository teamRepository;
    private CoachRepository coachRepository;


    @Override
    public Team findTeamFromUserEmail(String email) {
        CoachEntity coach = coachRepository.findByUserEntityEmail(email).orElseThrow(NO_COACH_EXCEPTION::new);
        return TeamConverter.convert(teamRepository.findByCoachesContains(coach).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new));
    }
}
