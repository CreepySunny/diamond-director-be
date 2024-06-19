package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl.FindCoachesFromTeamNameUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
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

public class FindCoachesFromTeamNameUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private FindCoachesFromTeamNameUseCaseImpl findCoachesFromTeamNameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCoachFromTeamName_Success() {
        String teamName = "Team A";

        CoachEntity coach1 = CoachEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        CoachEntity coach2 = CoachEntity.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        TeamEntity mockTeam = TeamEntity.builder()
                .teamId(1L)
                .teamName(teamName)
                .coaches(List.of(coach1, coach2))
                .players(List.of())
                .build();

        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.of(mockTeam));

        List<Coaches> coaches = findCoachesFromTeamNameUseCase.findCoachFromTeamName(teamName);

        assertNotNull(coaches);
        assertEquals(2, coaches.size());
        verify(teamRepository, times(1)).findByTeamName(teamName);
    }

    @Test
    void testFindCoachFromTeamName_NoTeamFound() {
        String teamName = "Nonexistent Team";

        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.empty());

        assertThrows(NO_TEAM_FOUND_EXCEPTION.class, () -> {
            findCoachesFromTeamNameUseCase.findCoachFromTeamName(teamName);
        });
        verify(teamRepository, times(1)).findByTeamName(teamName);
    }
}