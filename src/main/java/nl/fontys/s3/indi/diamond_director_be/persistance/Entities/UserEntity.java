package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.UserRoles;
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
    @ToString.Exclude
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CoachEntity coachEntity;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PlayerEntity playerEntity;
}
