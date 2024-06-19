package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.impl.FindGameFromGameIdUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindGameFromGameIdUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private FindGameFromGameIdUseCaseImpl findGameFromGameIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindGameFromGameId_Success() {
        Long gameId = 1L;
        GameEntity gameEntity = GameEntity.builder()
                .id(gameId)
                .homeTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team1").coaches(List.of()).build())
                .awayTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team2").coaches(List.of()).build())
                .plays(List.of())
                .outs(2)
                .season("TestSeason")
                .awayScore(2)
                .homeScore(1)
                .inning(5)
                .build();

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(gameEntity));

        Game game = findGameFromGameIdUseCase.findGameFromGameId(gameId);

        assertNotNull(game);
        assertEquals(gameId, game.getId());
        verify(gameRepository, times(1)).findById(gameId);
    }

    @Test
    void testFindGameFromGameId_NoGameFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(NO_GAME_EXCEPTION.class, () -> {
            findGameFromGameIdUseCase.findGameFromGameId(gameId);
        });
        verify(gameRepository, times(1)).findById(gameId);
    }
}
