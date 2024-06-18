package nl.fontys.s3.indi.diamond_director_be.Business.Team.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.DeleteTeamUsingTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTeamUsingTeamNameUseCaseImpl implements DeleteTeamUsingTeamNameUseCase {
    private final TeamRepository teamRepository;


    @Override
    @Transactional
    public void deleteTeamUsingTeamName(String teamName) {
        TeamEntity foundTeam = teamRepository.findByTeamName(teamName).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        teamRepository.delete(foundTeam);
    }
}
