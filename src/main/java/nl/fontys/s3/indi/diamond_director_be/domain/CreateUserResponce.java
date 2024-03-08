package nl.fontys.s3.indi.diamond_director_be.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponce {
    private long userId;
}
