package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.business.FindAllGameUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
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
