package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl.FindCoachesNoTeamUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindCoachesNoTeamUseCaseImplTest {

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private FindCoachesNoTeamUseCaseImpl findCoachesNoTeamUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void testFindCoachesWithNoTeam_Success() {
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

        when(coachRepository.findByTeamIsNull()).thenReturn(List.of(coach1, coach2));

        List<Coaches> coaches = findCoachesNoTeamUseCase.findCoachesWithNoTeam();

        assertNotNull(coaches);
        assertEquals(2, coaches.size());
        verify(coachRepository, times(1)).findByTeamIsNull();
    }

    @Test
    @Transactional
    void testFindCoachesWithNoTeam_NoCoachesFound() {
        when(coachRepository.findByTeamIsNull()).thenReturn(List.of());

        List<Coaches> coaches = findCoachesNoTeamUseCase.findCoachesWithNoTeam();

        assertNotNull(coaches);
        assertTrue(coaches.isEmpty());
        verify(coachRepository, times(1)).findByTeamIsNull();
    }
}