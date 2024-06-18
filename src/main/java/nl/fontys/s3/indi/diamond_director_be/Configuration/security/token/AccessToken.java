package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    UserRoles getUserRole();

    boolean hasRole(UserRoles roleName);
}
