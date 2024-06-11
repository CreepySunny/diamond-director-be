package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.CreateGameRequest;

public interface CreateGameUseCase {
    Long createGame(CreateGameRequest request);
}
