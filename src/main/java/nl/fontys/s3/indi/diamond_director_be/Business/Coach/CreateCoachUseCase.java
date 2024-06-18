package nl.fontys.s3.indi.diamond_director_be.Business.Coach;

import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;

public interface CreateCoachUseCase {
    CreateUserResponse createCoach(CreateCoachRequest request);
}
