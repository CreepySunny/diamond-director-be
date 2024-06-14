package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Team.CreateTeamRequest;

public interface CreateTeamUseCase {
    Long createTeam(CreateTeamRequest request);
}
