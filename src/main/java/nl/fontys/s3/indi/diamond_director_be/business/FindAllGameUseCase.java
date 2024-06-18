package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;

import java.util.List;

public interface FindAllGameUseCase {
    List<Game> findAll();
}
