package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.CreateUserUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserResponce;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.impl.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserUseCaseTest {

    @Test
    public void testCreateUser() {
        // Create useCase and Implementation
        // Create useCase Dependency
        UserRepository userRepository = new FakeUserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl(userRepository);

        // Prepare test data
        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john@example.com", "password123", "user");

        // Call Create USer
        CreateUserResponce response = createUserUseCase.CreateUser(request);

        // Assertions
        assertNotNull(response);
        assertNotNull(response.getUserId());
        assertTrue(response.getUserId() > 0); // Assuming user ID is generated and greater than 0
    }
}