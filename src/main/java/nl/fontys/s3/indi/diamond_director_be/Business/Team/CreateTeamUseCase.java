package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Domain.Team.CreateTeamRequest;

public interface CreateTeamUseCase {
    Long createTeam(CreateTeamRequest request);
}
