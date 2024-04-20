package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.business.impl.GetAllUsersUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import nl.fontys.s3.indi.diamond_director_be.domain.GetAllUsersResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class GetAllUsersUseCaseTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    GetAllUsersUseCaseImpl getAllUsersUseCase;
    @Test
    void testGetAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(UserEntity.builder()
                .id(1L)
                .email("john@example.com")
                .password("password123")
                .role("user")
                .build());
        userEntities.add(UserEntity.builder()
                .id(2L)
                .email("jane@example.com")
                .password("password456")
                .role("admin")
                .build());

        when(userRepository.findAll()).thenReturn(userEntities);

        // When
        GetAllUsersResponce response = getAllUsersUseCase.getAllUsers();

        // Then
        List<User> expectedUsers = userEntities.stream()
                .map(e -> User.builder()
                        .id(e.getId())
                        .email(e.getEmail())
                        .password(e.getPassword())
                        .role(e.getRole())
                        .build())
                .collect(Collectors.toList());

        assertEquals(expectedUsers, response.getAllUsers());
    }
}