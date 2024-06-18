package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;

public interface FindGameFromGameIdUseCase {
    Game findGameFromGameId(Long gameId);
}
