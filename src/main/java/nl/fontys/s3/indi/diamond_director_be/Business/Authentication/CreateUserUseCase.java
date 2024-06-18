package nl.fontys.s3.indi.diamond_director_be.Business.Authentication;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse CreateUser (CreateUserRequest request);
}
