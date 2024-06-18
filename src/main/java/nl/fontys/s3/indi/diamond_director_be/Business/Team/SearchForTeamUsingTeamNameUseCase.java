package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;

public interface SearchForTeamUsingTeamNameUseCase {
    Team searchForTeam(String teamName);
}
