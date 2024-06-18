package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignCoachTeamRequest;

public interface AssignCoachToTeamUseCase {
    void assignCoachToTeam(AssignCoachTeamRequest request);
}
