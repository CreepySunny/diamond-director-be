package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.DUP_EMAIL_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
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

        UserEntity userEntityToSave = UserEntity
                .builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .build();

            CoachEntity coachEntityToSave = CoachEntity
                    .builder()
                    .userEntity(userEntityToSave)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .dateOfBirth(request.getDateOfBirth())
                    .canScoreKeep(false)
                    .position(request.getPosition())
                    .build();

            coachEntityToSave = saveCoach(coachEntityToSave);

            return CreateUserResponse.builder().userId(coachEntityToSave.getUserEntity().getId()).build();
    }


    private CoachEntity saveCoach(CoachEntity coachToSave){
        Optional<UserEntity> maybeAExistingUser = userRepository.findByEmail(coachToSave.getUserEntity().getEmail());
        if (maybeAExistingUser.isEmpty()){
            throw new DUP_EMAIL_EXCEPTION();
        }

        return coachRepository.save(coachToSave);
    }
}
