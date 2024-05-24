package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.UserConverter;
import nl.fontys.s3.indi.diamond_director_be.business.GetUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
       Optional<UserEntity> userEntity = userRepository.findById(id);
       if(userEntity.isPresent()){
           return UserConverter.convert(userEntity.get());
       }
       return null;
    }
}
