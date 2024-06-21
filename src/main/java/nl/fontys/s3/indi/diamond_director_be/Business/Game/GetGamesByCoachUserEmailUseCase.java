package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.GameResponse;

import java.util.List;

public interface GetGamesByCoachUserEmailUseCase {
    List<GameResponse> findGamesByCoachUserEmail(String coachId);
}
