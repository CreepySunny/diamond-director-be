package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Business.Game.impl.FindAllGameUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FindAllGameUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private FindAllGameUseCaseImpl findAllGameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_Success() {
        List<GameEntity> gameEntities = Arrays.asList(
                GameEntity.builder()
                        .id(1L)
                        .homeTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team1").coaches(List.of()).build())
                        .awayTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team2").coaches(List.of()).build())
                        .plays(List.of())
                        .outs(2)
                        .season("TestSeason")
                        .awayScore(2)
                        .homeScore(1)
                        .inning(5)
                        .build(),
        GameEntity.builder()
                .id(2L)
                .homeTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team3").coaches(List.of()).build())
                .awayTeam(TeamEntity.builder().players(List.of()).teamId(1L).teamName("Team4").coaches(List.of()).build())
                .plays(List.of())
                .outs(2)
                .season("TestSeason")
                .awayScore(2)
                .homeScore(1)
                .inning(5)
                .build()
        );

        when(gameRepository.findAll()).thenReturn(gameEntities);

        List<Game> games = findAllGameUseCase.findAll();

        assertNotNull(games);
        assertEquals(2, games.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        when(gameRepository.findAll()).thenReturn(Arrays.asList());

        List<Game> games = findAllGameUseCase.findAll();

        assertNotNull(games);
        assertTrue(games.isEmpty());
        verify(gameRepository, times(1)).findAll();
    }
}