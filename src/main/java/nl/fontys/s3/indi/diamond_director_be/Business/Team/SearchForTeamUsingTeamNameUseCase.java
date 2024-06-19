package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;

import java.util.List;

public interface SearchForTeamUsingTeamNameUseCase {
    List<Team> searchForTeam(String teamName);
}
