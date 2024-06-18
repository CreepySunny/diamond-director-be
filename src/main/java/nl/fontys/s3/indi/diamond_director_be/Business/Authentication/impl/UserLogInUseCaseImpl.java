package nl.fontys.s3.indi.diamond_director_be.Business.Authentication.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.NO_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.UserLogInUseCase;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessTokenEncoder;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Impl.AccessTokenImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserLogInUseCaseImpl implements UserLogInUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    @Transactional
    public LogInResponse userAuthenticate(LogInRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.getEmail());
        UserEntity foundUser = optionalUser.orElseThrow(NO_EMAIL_EXCEPTION::new);

        LogInResponse response = new LogInResponse();

        if (!passwordMatch(request.getPassword(), foundUser.getPassword())){
            response.setAccessToken("");
        }
        response.setAccessToken(generateAccessToken(foundUser));

        return response;
    }

    private Boolean passwordMatch(String rawPass,String hashPass){
        return passwordEncoder.matches(rawPass, hashPass);
    }

    private String generateAccessToken(UserEntity user) {

        UserRoles role = user.getRole();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), user.getId(), role));
    }
}
