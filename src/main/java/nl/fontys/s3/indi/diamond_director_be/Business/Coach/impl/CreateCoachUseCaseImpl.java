package nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.DUP_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateCoachUseCaseImpl implements CreateCoachUseCase {
    private final CoachRepository coachRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createCoach(CreateCoachRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        checkForEmail(request.getEmail());

        UserEntity userEntityToSave = UserEntity
                .builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(UserRoles.COACH)
                .build();

        userEntityToSave = userRepository.save(userEntityToSave);


            CoachEntity coachEntityToSave = CoachEntity
                    .builder()
                    .userEntity(userEntityToSave)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .dateOfBirth(request.getDateOfBirth())
                    .canScoreKeep(false)
                    .position(request.getPosition())
                    .build();

            coachEntityToSave = coachRepository.save(coachEntityToSave);

            return CreateUserResponse.builder().userId(coachEntityToSave.getUserEntity().getId()).build();
    }


    private void checkForEmail(String email){
        Optional<UserEntity> maybeAExistingUser = userRepository.findByEmail(email);

        if(maybeAExistingUser.isPresent()){
            throw new DUP_EMAIL_EXCEPTION();
        }
    }
}
