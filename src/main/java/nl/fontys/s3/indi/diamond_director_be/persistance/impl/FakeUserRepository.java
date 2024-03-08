package nl.fontys.s3.indi.diamond_director_be.persistance.impl;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepository implements UserRepository {
    private final List<UserEntity> savedUsers;
    private static long nextId = 1;
    public FakeUserRepository() {this.savedUsers = new ArrayList<>(); }

    @Override
    public UserEntity createUser(UserEntity userToSave) {
        if (userToSave.getId() == null){
            userToSave.setId(nextId);
            nextId++;
            this.savedUsers.add(userToSave);
        }
        return userToSave;
    }

    @Override
    public Optional<UserEntity> findById(long userId) {
        return this.savedUsers.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst();
    }

    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    public int count(){
        return this.savedUsers.size();
    }
}
