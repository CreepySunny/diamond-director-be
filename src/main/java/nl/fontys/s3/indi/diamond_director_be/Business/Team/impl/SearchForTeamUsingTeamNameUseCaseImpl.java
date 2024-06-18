package nl.fontys.s3.indi.diamond_director_be.Business.Team.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.SearchForTeamUsingTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchForTeamUsingTeamNameUseCaseImpl implements SearchForTeamUsingTeamNameUseCase {
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Team searchForTeam(String teamName) {
        return TeamConverter.convert(teamRepository.findByTeamNameContainingIgnoreCase(teamName).orElseThrow(NO_COACH_EXCEPTION::new));
    }
}