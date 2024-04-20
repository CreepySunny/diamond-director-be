package nl.fontys.s3.indi.diamond_director_be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}