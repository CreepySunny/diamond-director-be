package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl.CreateCoachUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.DUP_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.*;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCoachUseCaseImplTest {

    @Mock
    private CoachRepository coachRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateCoachUseCaseImpl createCoachUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCoach() {
        // Given
        CreateCoachRequest request = CreateCoachRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email@example.com")
                .password("Password")
                .role(UserRoles.COACH)
                .dateOfBirth(LocalDate.now())
                .position(CoachPosition.OFFENSE)
                .canScoreKeep(false)
                .build();

        String encodedPassword = "encodedPassword"; // This should be the expected encoded password
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);

        UserEntity userEntityToSave = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .id(1L)
                .build();

        CoachEntity coachEntityToSave = CoachEntity.builder()
                .userEntity(userEntityToSave)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .position(request.getPosition())
                .canScoreKeep(request.getCanScoreKeep())
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(coachRepository.save(any(CoachEntity.class))).thenReturn(coachEntityToSave);

        // When
        CreateUserResponse response = createCoachUseCase.createCoach(request);

        // Then
        assertEquals(coachEntityToSave.getUserEntity().getId(), response.getUserId());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(coachRepository, times(1)).save(any(CoachEntity.class));
    }

    @Test
    public void testCreateCoachThrowDubEmailException() {
        // Given
        CreateCoachRequest request = CreateCoachRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email@example.com")
                .password("Password")
                .role(UserRoles.COACH)
                .dateOfBirth(LocalDate.now())
                .position(CoachPosition.OFFENSE)
                .canScoreKeep(false)
                .build();

        String encodedPassword = "encodedPassword"; // This should be the expected encoded password
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);

        UserEntity userEntityToSave = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .id(1L)
                .build();

        CoachEntity coachEntityToSave = CoachEntity.builder()
                .userEntity(userEntityToSave)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .position(request.getPosition())
                .canScoreKeep(request.getCanScoreKeep())
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userEntityToSave));
        when(coachRepository.save(any(CoachEntity.class))).thenReturn(coachEntityToSave);

        // When
        assertThrows(DUP_EMAIL_EXCEPTION.class, () -> createCoachUseCase.createCoach(request));

    }


}