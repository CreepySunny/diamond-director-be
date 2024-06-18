package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.GameResponse;

import java.util.List;

public interface GetGamesByCoachUserEmailUseCse {
    List<GameResponse> findGamesByCoachUserEmail(String coachId);
}
