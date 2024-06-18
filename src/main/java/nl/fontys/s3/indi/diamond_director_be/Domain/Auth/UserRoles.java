package nl.fontys.s3.indi.diamond_director_be.Domain.Auth;

import lombok.Getter;

@Getter
public enum UserRoles {
    PLAYER,
    COACH,
    SCOREKEEPER,
    ADMIN
}
