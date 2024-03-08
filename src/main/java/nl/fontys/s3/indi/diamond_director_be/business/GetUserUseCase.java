package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(Long id);
}
