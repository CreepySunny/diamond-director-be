package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl.AssignCoachToTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignCoachTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AssignCoachToTeamUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private AssignCoachToTeamUseCaseImpl assignCoachToTeamUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void testAssignCoachToTeam_Success() {
        Long coachId = 1L;
        String teamName = "Team A";
        AssignCoachTeamRequest request = new AssignCoachTeamRequest(teamName, coachId);

        CoachEntity mockCoach = CoachEntity.builder()
                .id(coachId)
                .firstName("John")
                .lastName("Doe")
                .build();

        TeamEntity mockTeam = TeamEntity.builder()
                .teamId(1L)
                .teamName("Team A")
                .coaches(List.of())
                .players(List.of())
                .build();

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(mockCoach));
        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.of(mockTeam));

        assignCoachToTeamUseCase.assignCoachToTeam(request);

        verify(coachRepository).save(mockCoach);
        assertEquals(mockTeam, mockCoach.getTeam());
    }

    @Test
    @Transactional
    void testAssignCoachToTeam_NoCoachFound() {
        Long coachId = 1L;
        String teamName = "Team A";
        AssignCoachTeamRequest request = new AssignCoachTeamRequest(teamName, coachId);

        when(coachRepository.findById(coachId)).thenReturn(Optional.empty());

        assertThrows(NO_COACH_EXCEPTION.class, () -> {
            assignCoachToTeamUseCase.assignCoachToTeam(request);
        });
    }

    @Test
    @Transactional
    void testAssignCoachToTeam_NoTeamFound() {
        Long coachId = 1L;
        String teamName = "Team A";
        AssignCoachTeamRequest request = new AssignCoachTeamRequest(teamName, coachId);

        CoachEntity mockCoach = CoachEntity.builder()
                .id(coachId)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(mockCoach));
        when(teamRepository.findByTeamName(teamName)).thenReturn(Optional.empty());

        assertThrows(NO_TEAM_FOUND_EXCEPTION.class, () -> {
            assignCoachToTeamUseCase.assignCoachToTeam(request);
        });
    }
}