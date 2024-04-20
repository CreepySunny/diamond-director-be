package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Table(name = "user_account")
@Builder @AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "role")
    private String role;
}
