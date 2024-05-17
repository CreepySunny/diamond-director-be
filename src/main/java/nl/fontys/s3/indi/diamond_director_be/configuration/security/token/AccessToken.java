package nl.fontys.s3.indi.diamond_director_be.configuration.security.token;

import nl.fontys.s3.indi.diamond_director_be.domain.Enums.UserRoles;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    UserRoles getUserRole();

    boolean hasRole(UserRoles roleName);
}
