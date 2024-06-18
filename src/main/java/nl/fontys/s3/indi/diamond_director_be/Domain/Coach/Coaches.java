package nl.fontys.s3.indi.diamond_director_be.Domain.Coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.User;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Coaches extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private CoachPosition position;
    private Boolean canScoreKeep;
}
