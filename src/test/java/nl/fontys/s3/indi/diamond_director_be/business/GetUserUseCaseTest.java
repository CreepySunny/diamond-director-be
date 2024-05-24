package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.GetUserUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void testGetUser_WhenUserExists() {
        // Given
        Long userId = 1L;
        UserEntity userEntity = UserEntity.builder()
                .id(userId)
                .email("john@example.com")
                .password("password123")
                .role(UserRoles.ADMIN)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // When
        User user = getUserUseCase.getUser(userId);

        // Then
        assertEquals(userId, user.getId());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(UserRoles.ADMIN, user.getRole());
    }

    @Test
    void testGetUser_WhenUserDoesNotExist() {
        // Given
        Long userId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        User user = getUserUseCase.getUser(userId);

        // Then
        assertNull(user);
    }
}