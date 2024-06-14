package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.CoachConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindCoachesFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindCoachesFromTeamNameUseCaseImpl implements FindCoachesFromTeamNameUseCase {
    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<Coaches> findCoachFromTeamName(String teamName) {
        TeamEntity foundTeam = teamRepository.findByTeamName(teamName).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        return coachRepository.findCoachEntitiesByTeamIs(foundTeam).stream()
                .map(CoachConverter::convert)
                .toList();
    }
}
