package nl.fontys.s3.indi.diamond_director_be.Business.Team.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.FindAllTeamsUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
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
