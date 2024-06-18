package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;

public interface SearchForTeamUsingTeamNameUseCase {
    Team searchForTeam(String teamName);
}
