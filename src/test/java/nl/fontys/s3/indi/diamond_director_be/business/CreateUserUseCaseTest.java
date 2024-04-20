package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.CreateUserUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserResponce;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void testCreateUser() {
        // Given
        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john@example.com", "password123", "user");

        UserEntity savedUser = UserEntity.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .id(1L) // Assuming the user ID will be set after saving
                .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // When
        CreateUserResponce response = createUserUseCase.CreateUser(request);

        // Then
        assertNotNull(response);
        assertNotNull(response.getUserId());
        assertEquals(1L, response.getUserId()); // Assuming user ID is generated and equals 1

        // Verify that userRepository.save() is called once with any UserEntity object
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}