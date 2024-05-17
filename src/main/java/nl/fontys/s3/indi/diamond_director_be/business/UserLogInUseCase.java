package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.LogInRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.LogInResponse;

public interface UserLogInUseCase {
    LogInResponse userAuthenticate(LogInRequest request);
}
