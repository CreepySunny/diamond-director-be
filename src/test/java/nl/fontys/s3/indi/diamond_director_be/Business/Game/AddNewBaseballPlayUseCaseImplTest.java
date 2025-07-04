package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.impl.AddNewBaseballPlayUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.ScoreRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.*;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
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

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayFielderRepository playFielderRepository;

    @InjectMocks
    private AddNewBaseballPlayUseCaseImpl addNewBaseballPlayUseCase;

    private ScoreRequest scoreRequest;
    private GameEntity gameEntity;
    private Game game;
    private PlayerEntity batterEntity;
    private Player batter;
    private PlayerEntity pitcherEntity;
    private Player pitcher;
    private TeamEntity awayTeamEntity;
    private TeamEntity homeTeamEntity;

    @BeforeEach
    void setUp() {
        scoreRequest = new ScoreRequest();
        scoreRequest.setGameId(1L);
        scoreRequest.setBatterId(2L);
        scoreRequest.setPitcherId(3L);
        scoreRequest.setPlayShorthand("1B");
        scoreRequest.setInning(1);
        scoreRequest.setFieldersPositions(List.of(PlayerPosition.catcher, PlayerPosition.reliever, PlayerPosition.right_field, PlayerPosition.shortstop));
        scoreRequest.setHalf(InningHalves.TOP);

        batterEntity = new PlayerEntity();
        batterEntity.setId(2L);
        batterEntity.setFirstName("Batter");

        pitcherEntity = new PlayerEntity();
        pitcherEntity.setId(3L);
        pitcherEntity.setFirstName("Pitcher");

        batter = PlayerConverter.convert(batterEntity);
        pitcher = PlayerConverter.convert(pitcherEntity);

        awayTeamEntity = TeamEntity.builder()
                .teamId(1L)
                .teamName("Team1")
                .players(List.of())
                .coaches(List.of())
                .build();

        homeTeamEntity = TeamEntity.builder()
                .teamId(2L)
                .teamName("Team2")
                .players(List.of())
                .coaches(List.of())
                .build();

        gameEntity = new GameEntity();
        gameEntity.setId(1L);
        gameEntity.setInning(1);
        gameEntity.setOuts(0);
        gameEntity.setAwayScore(0);
        gameEntity.setHomeScore(0);
        gameEntity.setHalves(InningHalves.TOP);
        gameEntity.setPlays(List.of(PlayEntity.builder().id(1L).playResult(PlayResult.DOUBLE).batter(batterEntity).pitcher(pitcherEntity).build()));
        gameEntity.setHomeTeam(homeTeamEntity);
        gameEntity.setAwayTeam(awayTeamEntity);

        game = GameConverter.convert(gameEntity);
        game.setBaseRunners(Map.of(Bases.FIRST, batter, Bases.SECOND, batter));
    }

    @Test
    void createNewPlayAndScore_success() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.of(pitcherEntity));
        when(playRepository.save(any(PlayEntity.class))).thenReturn(new PlayEntity());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(teamRepository.findAwayTeamByGameId(anyLong())).thenReturn(Optional.of(awayTeamEntity));
        when(teamRepository.findHomeTeamByGameId(anyLong())).thenReturn(Optional.of(homeTeamEntity));

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

    @Test
    void createNewPlayAndScore_inningChange() {
        gameEntity.setOuts(2);
        scoreRequest.setPlayShorthand("GO");

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.of(pitcherEntity));
        when(playRepository.save(any(PlayEntity.class))).thenReturn(new PlayEntity());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(teamRepository.findAwayTeamByGameId(anyLong())).thenReturn(Optional.of(awayTeamEntity));
        when(teamRepository.findHomeTeamByGameId(anyLong())).thenReturn(Optional.of(homeTeamEntity));

        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        assertNotNull(updatedGame);
        assertEquals(0, updatedGame.getOuts());
        assertEquals(1, updatedGame.getInning());
        assertEquals(InningHalves.BOTTOM, updatedGame.getCurrentHalf());
    }

    @Test
    void createNewPlayAndScore_advanceRunners() {
        scoreRequest.setPlayShorthand("2B");

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.of(pitcherEntity));
        when(playRepository.save(any(PlayEntity.class))).thenReturn(new PlayEntity());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(teamRepository.findAwayTeamByGameId(anyLong())).thenReturn(Optional.of(awayTeamEntity));
        when(teamRepository.findHomeTeamByGameId(anyLong())).thenReturn(Optional.of(homeTeamEntity));

        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        assertNotNull(updatedGame);
        assertEquals(1, updatedGame.getBaseRunners().size());
        assertEquals(batter, updatedGame.getBaseRunners().get(Bases.SECOND));
    }

    @Test
    void createNewPlayAndScore_sacrificeFly() {
        scoreRequest.setPlayShorthand("SF");

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(batterEntity));
        when(playerRepository.findById(3L)).thenReturn(Optional.of(pitcherEntity));
        when(playRepository.save(any(PlayEntity.class))).thenReturn(new PlayEntity());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(teamRepository.findAwayTeamByGameId(anyLong())).thenReturn(Optional.of(awayTeamEntity));
        when(teamRepository.findHomeTeamByGameId(anyLong())).thenReturn(Optional.of(homeTeamEntity));

        Game updatedGame = addNewBaseballPlayUseCase.createNewPlayAndScore(scoreRequest);

        assertNotNull(updatedGame);
        assertEquals(1, updatedGame.getOuts());
    }
}