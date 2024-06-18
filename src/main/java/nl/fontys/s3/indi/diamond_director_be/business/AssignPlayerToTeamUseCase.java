package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Team.AssignPlayerTeamRequest;

public interface AssignPlayerToTeamUseCase {
    void assignPlayerToTeam(AssignPlayerTeamRequest request);
}
