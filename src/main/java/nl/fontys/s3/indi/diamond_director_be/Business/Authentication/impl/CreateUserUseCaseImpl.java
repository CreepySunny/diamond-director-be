package nl.fontys.s3.indi.diamond_director_be.Business.Authentication.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.CreateUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse CreateUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity savedUser= userRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .password(encodedPassword)
                .build()
            );

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }
}
