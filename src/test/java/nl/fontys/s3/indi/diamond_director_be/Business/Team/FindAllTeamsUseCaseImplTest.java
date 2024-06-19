package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Business.Team.impl.FindAllTeamsUseCaseImpl;
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

public class FindAllTeamsUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private FindAllTeamsUseCaseImpl findAllTeamsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllTeams() {
        TeamEntity team1 = TeamEntity.builder()
                .teamId(1L)
                .teamName("Team A")
                .coaches(List.of())
                .players(List.of())
                .build();


        TeamEntity team2 = TeamEntity.builder()
                .teamId(2L)
                .teamName("Team B")
                .coaches(List.of())
                .players(List.of())
                .build();

        List<TeamEntity> teamEntities = Arrays.asList(team1, team2);

        when(teamRepository.findAll()).thenReturn(teamEntities);

        List<Team> teams = findAllTeamsUseCase.findAllTeams();

        assertNotNull(teams);
        assertEquals(2, teams.size());

        assertEquals(1L, teams.get(0).getId());
        assertEquals("Team A", teams.get(0).getTeamName());

        assertEquals(2L, teams.get(1).getId());
        assertEquals("Team B", teams.get(1).getTeamName());

        verify(teamRepository, times(1)).findAll();
    }
}
