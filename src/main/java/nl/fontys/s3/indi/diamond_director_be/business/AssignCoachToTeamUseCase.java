package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Team.AssignCoachTeamRequest;

public interface AssignCoachToTeamUseCase {
    void assignCoachToTeam(AssignCoachTeamRequest request);
}
