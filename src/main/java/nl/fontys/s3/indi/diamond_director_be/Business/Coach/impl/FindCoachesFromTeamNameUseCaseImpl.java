package nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Converter.CoachConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindCoachesFromTeamNameUseCaseImpl implements FindCoachesFromTeamNameUseCase {
    private final TeamRepository teamRepository;

    @Override
    public List<Coaches> findCoachFromTeamName(String teamName) {
        TeamEntity foundTeam = teamRepository.findByTeamName(teamName).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        return foundTeam.getCoaches().stream()
                .map(CoachConverter::convert)
                .toList();
    }
}
