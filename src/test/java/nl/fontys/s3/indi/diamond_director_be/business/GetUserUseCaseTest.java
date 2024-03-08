package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.GetUserUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.User;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.impl.FakeUserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetUserUseCaseTest {

    @Test
    public void testGetUser() {
        UserRepository userRepository = new FakeUserRepository();
        GetUserUseCase getUserUseCase = new GetUserUseCaseImpl(userRepository);
        Long userId = 1L;

        userRepository.createUser(
                UserEntity.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john@example.com")
                        .password("password123")
                        .role("Coach")
                        .build()
        );
        
        Optional<User> result = getUserUseCase.getUser(userId);

        // Assertions
        assertTrue(result.isPresent());
        User user = result.get();
        assertEquals(userId, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("Coach", user.getRole());
    }
}