package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Business.Team.impl.SearchForTeamUsingTeamNameUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchForTeamUsingTeamNameUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private SearchForTeamUsingTeamNameUseCaseImpl searchForTeamUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchForTeam() {
        // Given
        String searchTerm = "team";
        TeamEntity teamEntity1 = TeamEntity.builder()
                .teamId(1L)
                .teamName("Team Alpha")
                .players(List.of())
                .coaches(List.of())
                .build();

        TeamEntity teamEntity2 = TeamEntity.builder()
                .teamId(2L)
                .teamName("Beta Team")
                .players(List.of())
                .coaches(List.of())
                .build();

        List<TeamEntity> teamEntities = Arrays.asList(teamEntity1, teamEntity2);

        when(teamRepository.findByTeamNameContainingIgnoreCase(searchTerm)).thenReturn(teamEntities);

        List<Team> teams = searchForTeamUseCase.searchForTeam(searchTerm);

        assertNotNull(teams);
        assertEquals(2, teams.size());

        assertEquals(1L, teams.get(0).getId());
        assertEquals("Team Alpha", teams.get(0).getTeamName());

        assertEquals(2L, teams.get(1).getId());
        assertEquals("Beta Team", teams.get(1).getTeamName());

        verify(teamRepository, times(1)).findByTeamNameContainingIgnoreCase(searchTerm);
    }

    @Test
    void testSearchForTeam_NoResults() {
        String searchTerm = "nonexistent";

        when(teamRepository.findByTeamNameContainingIgnoreCase(searchTerm)).thenReturn(List.of());

        List<Team> teams = searchForTeamUseCase.searchForTeam(searchTerm);

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        verify(teamRepository, times(1)).findByTeamNameContainingIgnoreCase(searchTerm);
    }
}
