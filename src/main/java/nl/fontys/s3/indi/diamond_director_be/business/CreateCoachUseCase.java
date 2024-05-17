package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;

public interface CreateCoachUseCase {
    CreateUserResponse createCoach(CreateCoachRequest request);
}
