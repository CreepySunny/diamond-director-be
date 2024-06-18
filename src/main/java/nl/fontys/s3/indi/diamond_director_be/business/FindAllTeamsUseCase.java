package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;

import java.util.List;

public interface FindAllTeamsUseCase {
    List<Team> findAllTeams();
}
