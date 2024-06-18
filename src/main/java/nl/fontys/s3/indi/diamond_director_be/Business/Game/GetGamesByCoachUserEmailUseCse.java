package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.GameResponse;

import java.util.List;

public interface GetGamesByCoachUserEmailUseCse {
    List<GameResponse> findGamesByCoachUserEmail(String coachId);
}
