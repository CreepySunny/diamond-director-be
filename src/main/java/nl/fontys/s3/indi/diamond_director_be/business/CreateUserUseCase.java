package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserResponce;

public interface CreateUserUseCase {
    CreateUserResponce CreateUser (CreateUserRequest request);
}
