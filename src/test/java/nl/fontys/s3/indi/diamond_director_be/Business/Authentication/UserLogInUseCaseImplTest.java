package nl.fontys.s3.indi.diamond_director_be.Business.Authentication;

import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.impl.UserLogInUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.NO_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessTokenEncoder;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Impl.AccessTokenImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInResponse;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserLogInUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private UserLogInUseCaseImpl userLogInUseCase;

    private LogInRequest request;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        request = new LogInRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("hashedPassword");
        userEntity.setRole(UserRoles.PLAYER);
        userEntity.setId(1L);
    }

    @Test
    void userAuthenticate_successfulLogin() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessTokenImpl.class))).thenReturn("token");

        LogInResponse response = userLogInUseCase.userAuthenticate(request);

        assertNotNull(response);
        assertEquals("token", response.getAccessToken());
    }

    @Test
    void userAuthenticate_invalidPassword() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        LogInResponse response = userLogInUseCase.userAuthenticate(request);

        assertNotNull(response);
        assertNull(response.getAccessToken());
    }

    @Test
    void userAuthenticate_userNotFound() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        assertThrows(NO_EMAIL_EXCEPTION.class, () -> {
            userLogInUseCase.userAuthenticate(request);
        });
    }
}
