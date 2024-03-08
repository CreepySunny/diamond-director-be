package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity createUser(UserEntity userToSave);
    Optional<UserEntity> findById(long userId);
    List<UserEntity> findAll();
    int count();
}
