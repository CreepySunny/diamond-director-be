package nl.fontys.s3.indi.diamond_director_be.configuration.security.token.Impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.fontys.s3.indi.diamond_director_be.configuration.security.token.AccessToken;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.UserRoles;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken{

        private final String subject;
        private final Long userId;
        private final Set<UserRoles> roles;

        public AccessTokenImpl(String subject, Long userId, Collection<UserRoles> roles) {
            this.subject = subject;
            this.userId = userId;
            this.roles = roles != null ? Set.copyOf(roles) : Collections.emptySet();
        }

        @Override
        public boolean hasRole(UserRoles roleName) {
            return this.roles.contains(roleName);
        }
    }

