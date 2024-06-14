package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;

import java.util.List;

public interface FindCoachesNoTeamUseCase {
    List<Coaches> findCoachesWithNoTeam();
}
