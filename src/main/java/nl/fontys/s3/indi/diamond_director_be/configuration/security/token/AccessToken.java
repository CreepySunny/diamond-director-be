package nl.fontys.s3.indi.diamond_director_be.configuration.security.token;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.UserRoles;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    UserRoles getUserRole();

    boolean hasRole(UserRoles roleName);
}
