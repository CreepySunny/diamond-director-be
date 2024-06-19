package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.GetAllPlayersFromGameIdUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetAllPlayersFromGameIdUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GetAllPlayersFromGameIdUseCaseImpl getAllPlayersFromGameIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlayersFromGameId_Success() {
        Long gameId = 1L;
        GameEntity mockGameEntity = createMockGameEntity(gameId);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(mockGameEntity));

        List<Player> players = getAllPlayersFromGameIdUseCase.getAllPlayersFromGameId(gameId);

        assertNotNull(players);
        assertEquals(6, players.size());
        assertEquals("Player1", players.get(0).getFirstName());
    }

    @Test
    void testGetAllPlayersFromGameId_NoGameFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(NO_GAME_EXCEPTION.class, () -> {
            getAllPlayersFromGameIdUseCase.getAllPlayersFromGameId(gameId);
        });
    }

    private GameEntity createMockGameEntity(Long gameId) {
        TeamEntity homeTeam = createMockTeamEntity(1L, "Home Team", 3);
        TeamEntity awayTeam = createMockTeamEntity(2L, "Away Team", 3);

        return GameEntity.builder()
                .id(gameId)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .build();
    }

    private TeamEntity createMockTeamEntity(Long teamId, String teamName, int playerCount) {
        List<PlayerEntity> players = Stream.iterate(1, i -> i + 1)
                .limit(playerCount)
                .map(i -> PlayerEntity.builder()
                        .id((long) i)
                        .firstName("Player" + i)
                        .lastName("Lastname" + i)
                        .build())
                .toList();

        return TeamEntity.builder()
                .teamId(teamId)
                .teamName(teamName)
                .players(players)
                .build();
    }
}
