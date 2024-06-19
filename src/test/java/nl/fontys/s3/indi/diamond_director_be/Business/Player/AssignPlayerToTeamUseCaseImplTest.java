package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.AssignPlayerToTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignPlayerTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssignPlayerToTeamUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private AssignPlayerToTeamUseCaseImpl assignPlayerToTeamUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignPlayerToTeam() {
        AssignPlayerTeamRequest request = new AssignPlayerTeamRequest("TeamA", 1L);


        TeamEntity teamEntity = TeamEntity.builder()
                .teamId(1L)
                .teamName("TeamA")
                .players(List.of())
                .coaches(List.of())
                .build();


        PlayerEntity playerEntity = PlayerEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(teamRepository.findByTeamName("TeamA")).thenReturn(Optional.of(teamEntity));
        when(playerRepository.findById(1L)).thenReturn(Optional.of(playerEntity));

        assertDoesNotThrow(() -> assignPlayerToTeamUseCase.assignPlayerToTeam(request));

        verify(teamRepository, times(1)).findByTeamName("TeamA");
        verify(playerRepository, times(1)).findById(1L);
        verify(playerRepository, times(1)).save(playerEntity);

        assertEquals(teamEntity, playerEntity.getTeam());
    }

    @Test
    void testAssignPlayerToTeam_TeamNotFound() {
        AssignPlayerTeamRequest request = new AssignPlayerTeamRequest("NonexistentTeam", 1L);

        when(teamRepository.findByTeamName("NonexistentTeam")).thenReturn(Optional.empty());

        NO_TEAM_FOUND_EXCEPTION exception = assertThrows(NO_TEAM_FOUND_EXCEPTION.class,
                () -> assignPlayerToTeamUseCase.assignPlayerToTeam(request));

        assertEquals("404 NO_TEAM_FOUND", exception.getMessage());

        verify(teamRepository, times(1)).findByTeamName("NonexistentTeam");
        verify(playerRepository, never()).findById(any());
        verify(playerRepository, never()).save(any());
    }

    @Test
    void testAssignPlayerToTeam_PlayerNotFound() {
        AssignPlayerTeamRequest request = new AssignPlayerTeamRequest("TeamA", 999L);

        TeamEntity teamEntity = TeamEntity.builder()
                .teamId(1L)
                .teamName("TeamA")
                .coaches(List.of())
                .players(List.of())
                .build();

        when(teamRepository.findByTeamName("TeamA")).thenReturn(Optional.of(teamEntity));
        when(playerRepository.findById(999L)).thenReturn(Optional.empty());

        NO_PLAYER_EXCEPTION exception = assertThrows(NO_PLAYER_EXCEPTION.class,
                () -> assignPlayerToTeamUseCase.assignPlayerToTeam(request));

        assertEquals("404 NO_PLAYER_BY_ID", exception.getMessage());

        verify(teamRepository, times(1)).findByTeamName("TeamA");
        verify(playerRepository, times(1)).findById(999L);
        verify(playerRepository, never()).save(any());
    }
}
