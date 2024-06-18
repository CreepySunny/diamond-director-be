package nl.fontys.s3.indi.diamond_director_be.Business.Game.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.FindGameFromGameIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
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
