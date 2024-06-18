package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindGameFromGameIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindGameFromGameIdUseCaseImpl implements FindGameFromGameIdUseCase {
    private final GameRepository gameRepository;
    @Override
    public Game findGameFromGameId(Long gameId) {
        GameEntity foundGame = gameRepository.findById(gameId).orElseThrow(NO_GAME_EXCEPTION::new);
        return GameConverter.convert(foundGame);
    }
}
