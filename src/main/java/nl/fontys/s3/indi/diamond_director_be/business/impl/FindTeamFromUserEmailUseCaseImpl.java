package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindTeamFromUserEmailUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
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
