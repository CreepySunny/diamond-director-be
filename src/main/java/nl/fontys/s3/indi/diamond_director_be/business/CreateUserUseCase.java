package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse CreateUser (CreateUserRequest request);
}
