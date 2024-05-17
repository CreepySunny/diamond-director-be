package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
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
