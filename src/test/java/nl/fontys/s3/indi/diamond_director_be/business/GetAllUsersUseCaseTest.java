package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.GetAllUsersUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.impl.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import nl.fontys.s3.indi.diamond_director_be.domain.GetAllUsersResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.User;

import java.util.List;

public class GetAllUsersUseCaseTest {

    @Test
    void getAllUsersTest() {
        UserRepository userRepository = new FakeUserRepository();
        GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersUseCaseImpl(userRepository);

        userRepository.createUser(
                UserEntity.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john@example.com")
                        .password("password123")
                        .role("Coach")
                        .build()
        );

        userRepository.createUser(
                UserEntity.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("jane@example.com")
                        .password("password456")
                        .role("Player")
                        .build()
        );


        GetAllUsersResponce response = getAllUsersUseCase.getAllUsers();

        // Assertions
        assertNotNull(response);
        List<User> userList = response.getAllUsers();
        assertNotNull(userList);
        assertEquals(2, userList.size());

        // Verify the details of each user in the response
        User user1 = userList.get(0);
        assertEquals(1L, user1.getId());
        assertEquals("John", user1.getFirstName());
        assertEquals("Doe", user1.getLastName());
        assertEquals("john@example.com", user1.getEmail());
        assertEquals("password123", user1.getPassword());
        assertEquals("Coach", user1.getRole());

        User user2 = userList.get(1);
        assertEquals(2L, user2.getId());
        assertEquals("Jane", user2.getFirstName());
        assertEquals("Doe", user2.getLastName());
        assertEquals("jane@example.com", user2.getEmail());
        assertEquals("password456", user2.getPassword());
        assertEquals("Player", user2.getRole());
    }
}