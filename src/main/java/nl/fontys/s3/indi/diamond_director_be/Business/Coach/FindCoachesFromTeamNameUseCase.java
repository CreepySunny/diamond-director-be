package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;

import java.util.List;

public interface FindCoachesFromTeamNameUseCase {
    List<Coaches> findCoachFromTeamName(String teamName);
}
