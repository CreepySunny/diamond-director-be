package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.CreateUserUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreateUser() {
        // Given
        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john@example.com", "password123", UserRoles.ADMIN);

        UserEntity savedUser = UserEntity.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .id(1L) // Assuming the user ID will be set after saving
                .build();
        String encodedPassword = "encodedPassword"; // This should be the expected encoded password
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // When
        CreateUserResponse response = createUserUseCase.CreateUser(request);

        // Then
        assertNotNull(response);
        assertNotNull(response.getUserId());
        assertEquals(1L, response.getUserId()); // Assuming user ID is generated and equals 1

        // Verify that userRepository.save() is called once with any UserEntity object
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}