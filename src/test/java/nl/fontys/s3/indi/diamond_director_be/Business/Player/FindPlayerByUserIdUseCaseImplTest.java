package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.FindPlayerByUserIdUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerHanded;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindPlayerByUserIdUseCaseImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private FindPlayerByUserIdUseCaseImpl findPlayerByUserIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void testFindPlayerByUserId_Success() {
        Long userId = 1L;
        PlayerEntity mockPlayerEntity = createMockPlayerEntity(userId);
        when(playerRepository.findByUserEntityId(userId)).thenReturn(Optional.of(mockPlayerEntity));

        Player player = findPlayerByUserIdUseCase.findPlayerByUserId(userId);

        assertNotNull(player);
        assertEquals(userId, player.getId());
        assertEquals("Player1", player.getFirstName());
        assertEquals("Lastname1", player.getLastName());
    }

    @Test
    @Transactional
    void testFindPlayerByUserId_NoPlayerFound() {
        Long userId = 1L;
        when(playerRepository.findByUserEntityId(userId)).thenReturn(Optional.empty());

        assertThrows(NO_COACH_EXCEPTION.class, () -> {
            findPlayerByUserIdUseCase.findPlayerByUserId(userId);
        });
    }

    private PlayerEntity createMockPlayerEntity(Long userId) {
        return PlayerEntity.builder()
                .id(userId)
                .firstName("Player1")
                .lastName("Lastname1")
                .handedBats(PlayerHanded.RIGHT)
                .handedThrows(PlayerHanded.RIGHT)
                .position(PlayerPosition.pitcher)
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .height(180.0)
                .weight(75.0)
                .build();
    }
}