package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.CreatePlayerUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerHanded;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreatePlayerUseCaseImplTest {

        @Mock
        private PlayerRepository playerRepository;

        @Mock
        private UserRepository userRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private CreatePlayerUseCaseImpl createPlayerUseCase;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        @Transactional
        public void testCreate() {

            CreatePlayerRequest request = CreatePlayerRequest.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .dateOfBirth(LocalDate.now())
                    .email("john@example.com")
                    .password("Password")
                    .position(PlayerPosition.catcher)
                    .handed_throws(PlayerHanded.RIGHT)
                    .handed_throws(PlayerHanded.RIGHT)
                    .height(1.69)
                    .weight(45.6)
                    .build();

            String encodedPassword = "encodedPassword"; // This should be the expected encoded password

            when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);

            UserEntity userEntityToSave = UserEntity.builder()
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .role(request.getRole())
                    .id(1L)
                    .build();

            PlayerEntity playerEntityToSave = PlayerEntity.builder()
                    .userEntity(userEntityToSave)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .handedThrows(request.getHanded_throws())
                    .handedBats(request.getHanded_bats())
                    .position(request.getPosition())
                    .dateOfBirth(request.getDateOfBirth())
                    .height(request.getHeight())
                    .weight(request.getWeight())
                    .build();

            when(playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntityToSave);
            when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntityToSave));
            when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityToSave);
            CreateUserResponse response = createPlayerUseCase.create(request);

            assertEquals(playerEntityToSave.getUserEntity().getId(), response.getUserId());
            verify(passwordEncoder, times(1)).encode(request.getPassword());
            verify(playerRepository, times(1)).save(any(PlayerEntity.class));
        }
    }