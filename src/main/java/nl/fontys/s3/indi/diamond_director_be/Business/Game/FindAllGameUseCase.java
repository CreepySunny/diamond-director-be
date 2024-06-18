package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;

import java.util.List;

public interface FindAllGameUseCase {
    List<Game> findAll();
}
