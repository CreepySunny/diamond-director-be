package nl.fontys.s3.indi.diamond_director_be.Domain.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private long userId;
}
