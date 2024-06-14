package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
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
        createTeamRequest.setCreatingUserId(1L);
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
        when(coachRepository.findByUserEntityId(anyLong())).thenReturn(Optional.of(coachEntity));
        when(teamRepository.save(any(TeamEntity.class))).thenReturn(teamEntity);

        Long teamId = createTeamUseCase.createTeam(createTeamRequest);

        assertNotNull(teamId);
        assertEquals(1L, teamId);
        verify(coachRepository, times(1)).findByUserEntityId(anyLong());
        verify(teamRepository, times(1)).save(any(TeamEntity.class));
    }

    @Test
    void createTeam_coachNotFound() {
        when(coachRepository.findByUserEntityId(anyLong())).thenReturn(Optional.empty());

        assertThrows(NO_COACH_EXCEPTION.class, () -> {
            createTeamUseCase.createTeam(createTeamRequest);
        });

        verify(coachRepository, times(1)).findByUserEntityId(anyLong());
        verify(teamRepository, times(0)).save(any(TeamEntity.class));
    }
}