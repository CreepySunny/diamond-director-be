package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.business.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddNewBaseballPlayUseCaseImplTest {

    @Mock
    private PlayRepository playRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private AddNewBaseballPlayUseCaseImpl addNewBaseballPlayUseCase;

    private ScoreRequest scoreRequest;
    private GameEntity gameEntity;
    private Game game;
    private PlayerEntity batterEntity;
    private Player batter;

    @BeforeEach
    void setUp() {
        scoreRequest = new ScoreRequest();
        scoreRequest.setGameId(1L);
        scoreRequest.setBatterId(2L);
        scoreRequest.setPitcherId(3L);
        scoreRequest.setPlayShorthand("1B");
        scoreRequest.setInning(1);
        scoreRequest.setHalf(InningHalves.TOP);

        batterEntity = new PlayerEntity();
        batterEntity.setId(2L);
        batterEntity.setFirstName("Batter");

        batter = PlayerConverter.convert(batterEntity);

        gameEntity = new GameEntity();
        gameEntity.setId(1L);
        gameEntity.setInning(1);
        gameEntity.setOuts(0);
        gameEntity.setAwayScore(0);
        gameEntity.setHomeScore(0);
        gameEntity.setPlays(List.of(PlayEntity.builder().id(1L).playResult(PlayResult.DOUBLE).batter(batterEntity).pitcher(batterEntity).build()));
        game = GameConverter.convert(gameEntity);
        game.setBaseRunners(Map.of(Bases.FIRST, batter, Bases.THIRD, batter, Bases.SECOND, batter));
    }

    @Test
    void createNewPlayAndScore_success() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.of(new PlayerEntity()));
        when(playRepository.save(any(PlayEntity.class))).thenReturn(new PlayEntity());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);

        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        assertNotNull(updatedGame);
        assertEquals(1, updatedGame.getBaseRunners().size());
        assertEquals(batter, updatedGame.getBaseRunners().get(Bases.FIRST));
    }

    @Test
    void createNewPlayAndScore_gameNotFound() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NO_GAME_EXCEPTION.class, () -> {
            addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);
        });
    }

    @Test
    void createNewPlayAndScore_batterNotFound() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);
        });
    }

    @Test
    void createNewPlayAndScore_pitcherNotFound() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(NO_PLAYER_EXCEPTION.class, () -> {
            addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);
        });
    }
}