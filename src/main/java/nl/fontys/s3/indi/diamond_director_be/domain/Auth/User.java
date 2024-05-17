package nl.fontys.s3.indi.diamond_director_be.domain.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.UserRoles;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private UserRoles role;
}