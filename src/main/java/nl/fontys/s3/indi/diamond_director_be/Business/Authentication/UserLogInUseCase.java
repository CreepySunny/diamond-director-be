package nl.fontys.s3.indi.diamond_director_be.Business.Authentication;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInResponse;

public interface UserLogInUseCase {
    LogInResponse userAuthenticate(LogInRequest request);
}
