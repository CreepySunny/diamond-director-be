package nl.fontys.s3.indi.diamond_director_be.Business.Team;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.impl.CreateTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTeamUseCaseImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private CreateTeamUseCaseImpl createTeamUseCase;

    private CreateTeamRequest createTeamRequest;
    private CoachEntity coachEntity;
    private TeamEntity teamEntity;

    @BeforeEach
    void setUp() {
        createTeamRequest = new CreateTeamRequest();
        createTeamRequest.setCreateCoachUserEmail("coach@prime.com");
        createTeamRequest.setTeamName("New Team");

        coachEntity = new CoachEntity();
        coachEntity.setId(1L);
        coachEntity.setUserEntity(UserEntity.builder().id(1L).build());

        teamEntity = TeamEntity.builder()
                .teamId(1L)
                .teamName("New Team")
                .coaches(List.of(coachEntity))
                .players(new ArrayList<>())
                .build();
    }

    @Test
    void createTeam_success() {
        when(coachRepository.findByUserEntityEmail(anyString())).thenReturn(Optional.of(coachEntity));
        when(teamRepository.save(any(TeamEntity.class))).thenReturn(teamEntity);

        Long teamId = createTeamUseCase.createTeam(createTeamRequest);

        assertNotNull(teamId);
        assertEquals(1L, teamId);
        verify(coachRepository, times(1)).findByUserEntityEmail(anyString());
        verify(teamRepository, times(1)).save(any(TeamEntity.class));
    }

    @Test
    void createTeam_coachNotFound() {
        when(coachRepository.findByUserEntityEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(NO_COACH_EXCEPTION.class, () -> {
            createTeamUseCase.createTeam(createTeamRequest);
        });

        verify(coachRepository, times(1)).findByUserEntityEmail(anyString());
        verify(teamRepository, times(0)).save(any(TeamEntity.class));
    }
}