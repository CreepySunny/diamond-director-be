package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.FindAllPlayersNoTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllPlayersNoTeamUseCaseImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private FindAllPlayersNoTeamUseCaseImpl findAllPlayersNoTeamUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPlayersNoTeam_Success() {
        List<PlayerEntity> mockPlayers = createMockPlayersWithoutTeam(3);
        when(playerRepository.findByTeamIsNull()).thenReturn(mockPlayers);

        List<Player> players = findAllPlayersNoTeamUseCase.findPlayersNoTeam();

        assertNotNull(players);
        assertEquals(3, players.size());
        assertEquals("Player1", players.get(0).getFirstName());
    }

    private List<PlayerEntity> createMockPlayersWithoutTeam(int count) {
        return Arrays.stream(new int[count])
                .mapToObj(i -> PlayerEntity.builder()
                        .id((long) (i + 1))
                        .firstName("Player" + (i + 1))
                        .lastName("Lastname" + (i + 1))
                        .build())
                .toList();
    }
}