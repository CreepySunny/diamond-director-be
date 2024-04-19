package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserResponce;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private UserRepository userRepository;

    @Override
    public CreateUserResponce CreateUser(CreateUserRequest request) {
        UserEntity savedUser= userRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .build()
            );

        return CreateUserResponce.builder()
                .userId(savedUser.getId())
                .build();
    }
}
