package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.DUP_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreatePlayerUseCaseImpl implements CreatePlayerUseCase {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public CreateUserResponse create(CreatePlayerRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntityToSave = UserEntity
                .builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(UserRoles.PLAYER)
                .build();

        UserEntity userEntity = userRepository.save(userEntityToSave);

        PlayerEntity savedPlayer = PlayerEntity.builder()
                        .userEntity(userEntity)
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .handedBats(request.getHanded_bats())
                        .dateOfBirth(request.getDateOfBirth())
                        .handedThrows(request.getHanded_throws())
                        .position(request.getPosition())
                        .height(request.getHeight())
                        .weight(request.getWeight())
                .build();

        savedPlayer = savePlayer(savedPlayer);

        return CreateUserResponse.builder().userId(savedPlayer.getUserEntity().getId()).build();
    }

    private PlayerEntity savePlayer(PlayerEntity playerEntityToSave){
        Optional<UserEntity> optionalUser = userRepository.findByEmail(playerEntityToSave.getUserEntity().getEmail());
        optionalUser.orElseThrow(DUP_EMAIL_EXCEPTION::new);

        return playerRepository.save(playerEntityToSave);
    }
}
