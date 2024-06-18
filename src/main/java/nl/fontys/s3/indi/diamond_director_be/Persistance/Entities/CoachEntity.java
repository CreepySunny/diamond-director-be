package nl.fontys.s3.indi.diamond_director_be.Persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CoachPosition;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "coaches")
@NoArgsConstructor
@AllArgsConstructor
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @NotNull
    @Column(name = "coach_position")
    @Enumerated(EnumType.STRING)
    private CoachPosition position;

    @NotNull
    @Column(name = "scorekeeper_perm")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean canScoreKeep;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
}
