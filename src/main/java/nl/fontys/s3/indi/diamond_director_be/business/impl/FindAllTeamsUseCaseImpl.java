package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.business.FindAllTeamsUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllTeamsUseCaseImpl implements FindAllTeamsUseCase {
    private final TeamRepository teamRepository;
    @Override
    public List<Team> findAllTeams() {
        return  teamRepository.findAll().stream()
                .map(TeamConverter::convert)
                .toList();
    }
}
