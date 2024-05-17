package nl.fontys.s3.indi.diamond_director_be.domain.Enums;

import io.micrometer.observation.ObservationFilter;
import lombok.Getter;

@Getter
public enum UserRoles {
    PLAYER,
    COACH,
    SCOREKEEPER,
    ADMIN
}
