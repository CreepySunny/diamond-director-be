package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;

public interface GetUserUseCase {
    User getUser(Long id);
}
