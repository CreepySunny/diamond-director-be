package nl.fontys.s3.indi.diamond_director_be.domain.Enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    PLAYER("PLAYER"),
    COACH ("COACH"),
    SCOREKEEPER ("SCOREKEEPER"),
    ADMIN ("ADMIN");

    private final String roleName;

    UserRoles(String roleName){
        this.roleName = roleName;
    }

}
