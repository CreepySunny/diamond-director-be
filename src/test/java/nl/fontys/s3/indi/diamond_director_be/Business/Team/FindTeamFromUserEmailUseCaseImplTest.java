package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.impl.FindTeamFromUserEmailUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class FindTeamFromUserEmailUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private FindTeamFromUserEmailUseCaseImpl findTeamFromUserEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTeamFromUserEmail_Success() {
        String userEmail = "test@example.com";
        CoachEntity coachEntity = CoachEntity.builder()
                .userEntity(UserEntity.builder().email(userEmail).build())
                .build();

        TeamEntity teamEntity = TeamEntity.builder()
                .teamId(1L)
                .teamName("Test Team")
                .coaches(List.of(coachEntity))
                .players(List.of())
                .build();

        when(coachRepository.findByUserEntityEmail(userEmail)).thenReturn(Optional.of(coachEntity));
        when(teamRepository.findByCoachesContains(coachEntity)).thenReturn(Optional.of(teamEntity));

        Team team = findTeamFromUserEmailUseCase.findTeamFromUserEmail(userEmail);

        assertNotNull(team);
        assertEquals(1L, team.getId());
        assertEquals("Test Team", team.getTeamName());

        verify(coachRepository, times(1)).findByUserEntityEmail(userEmail);
        verify(teamRepository, times(1)).findByCoachesContains(coachEntity);
    }

    @Test
    void testFindTeamFromUserEmail_CoachNotFound() {
        String userEmail = "nonexistent@example.com";

        when(coachRepository.findByUserEntityEmail(userEmail)).thenReturn(Optional.empty());

        NO_COACH_EXCEPTION exception = assertThrows(NO_COACH_EXCEPTION.class,
                () -> findTeamFromUserEmailUseCase.findTeamFromUserEmail(userEmail));

        assertEquals("404 NO_COACH_FOUND", exception.getMessage());

        verify(coachRepository, times(1)).findByUserEntityEmail(userEmail);
        verify(teamRepository, never()).findByCoachesContains(any());
    }

    @Test
    void testFindTeamFromUserEmail_TeamNotFound() {
        String userEmail = "test@example.com";
        CoachEntity coachEntity = CoachEntity.builder()
                .userEntity(UserEntity.builder().email(userEmail).build())
                .build();

        when(coachRepository.findByUserEntityEmail(userEmail)).thenReturn(Optional.of(coachEntity));
        when(teamRepository.findByCoachesContains(coachEntity)).thenReturn(Optional.empty());

        NO_TEAM_FOUND_EXCEPTION exception = assertThrows(NO_TEAM_FOUND_EXCEPTION.class,
                () -> findTeamFromUserEmailUseCase.findTeamFromUserEmail(userEmail));

        assertEquals("404 NO_TEAM_FOUND", exception.getMessage());

        verify(coachRepository, times(1)).findByUserEntityEmail(userEmail);
        verify(teamRepository, times(1)).findByCoachesContains(coachEntity);
    }
}
