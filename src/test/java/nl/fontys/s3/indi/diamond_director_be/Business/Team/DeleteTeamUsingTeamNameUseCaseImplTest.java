package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.impl.DeleteTeamUsingTeamNameUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeleteTeamUsingTeamNameUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private DeleteTeamUsingTeamNameUseCaseImpl deleteTeamUsingTeamNameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteTeamUsingTeamName_Success() {
        String teamName = "TestTeam";
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName(teamName);

        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.of(teamEntity));

        assertDoesNotThrow(() -> deleteTeamUsingTeamNameUseCase.deleteTeamUsingTeamName(teamName));

        verify(teamRepository, times(1)).findByTeamName(teamName);
        verify(teamRepository, times(1)).delete(teamEntity);
    }

    @Test
    void testDeleteTeamUsingTeamName_TeamNotFound() {
        String teamName = "NonExistentTeam";

        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.empty());

        NO_TEAM_FOUND_EXCEPTION exception = assertThrows(NO_TEAM_FOUND_EXCEPTION.class,
                () -> deleteTeamUsingTeamNameUseCase.deleteTeamUsingTeamName(teamName));

        assertEquals("404 NO_TEAM_FOUND", exception.getMessage());

        verify(teamRepository, times(1)).findByTeamName(teamName);
        verify(teamRepository, never()).delete(any());
    }
}
