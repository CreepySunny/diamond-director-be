package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;

public interface FindGameFromGameIdUseCase {
    Game findGameFromGameId(Long gameId);
}
