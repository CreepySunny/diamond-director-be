package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessToken;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken{

        private final String subject;
        private final Long userId;
        private final UserRoles userRole;

        public AccessTokenImpl(String subject, Long userId, UserRoles role) {
            this.subject = subject;
            this.userId = userId;
            this.userRole = role != null ? role : UserRoles.SCOREKEEPER;
        }

        @Override
        public boolean hasRole(UserRoles roleName) {
            return roleName == userRole;
        }
    }

