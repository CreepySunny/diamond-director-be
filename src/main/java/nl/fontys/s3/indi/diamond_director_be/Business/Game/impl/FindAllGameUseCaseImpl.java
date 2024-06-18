package nl.fontys.s3.indi.diamond_director_be.Business.Game.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.FindAllGameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllGameUseCaseImpl implements FindAllGameUseCase {
    private final GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameConverter::convert)
                .toList();
    }
}
