package nl.fontys.s3.indi.diamond_director_be.domain.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private long userId;
}
