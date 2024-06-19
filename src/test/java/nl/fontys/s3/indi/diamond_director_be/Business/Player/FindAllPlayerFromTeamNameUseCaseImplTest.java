package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.FindAllPlayerFromTeamNameUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllPlayerFromTeamNameUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private FindAllPlayerFromTeamNameUseCaseImpl findAllPlayersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPlayersFromTeamName_Success() {
        String teamName = "TeamA";
        TeamEntity mockTeam = createMockTeamWithPlayers(teamName, 3);
        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.of(mockTeam));

        List<Player> players = findAllPlayersUseCase.findPlayersFromTeamName(teamName);

        assertNotNull(players);
        assertEquals(3, players.size());
        assertEquals("Player1", players.get(0).getFirstName());
    }

    @Test
    void testFindPlayersFromTeamName_NoTeamFound() {
        String teamName = "NonExistentTeam";
        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.empty());

        assertThrows(NO_TEAM_FOUND_EXCEPTION.class, () -> {
            findAllPlayersUseCase.findPlayersFromTeamName(teamName);
        });
    }

    private TeamEntity createMockTeamWithPlayers(String teamName, int playerCount) {
        TeamEntity team = new TeamEntity();
        team.setTeamName(teamName);
        List<PlayerEntity> players = createMockPlayers(playerCount);
        team.setPlayers(players);
        return team;
    }

    private List<PlayerEntity> createMockPlayers(int count) {
        return Arrays.stream(new int[count])
                .mapToObj(i -> PlayerEntity.builder()
                        .id((long) (i + 1))
                        .firstName("Player" + (i + 1))
                        .lastName("Lastname" + (i + 1))
                        .build())
                .toList();
    }
}
