package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignPlayerTeamRequest;

public interface AssignPlayerToTeamUseCase {
    void assignPlayerToTeam(AssignPlayerTeamRequest request);
}
