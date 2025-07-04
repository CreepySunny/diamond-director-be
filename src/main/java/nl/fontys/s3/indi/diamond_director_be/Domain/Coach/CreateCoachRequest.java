package nl.fontys.s3.indi.diamond_director_be.Domain.Coach;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserRequest;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateCoachRequest extends CreateUserRequest {

    @NotNull(message = "Dob Cannot be empty!")
    private LocalDate dateOfBirth;

    @NotNull(message = "Coach Position cannot be empty!")
    private CoachPosition position;

    @NotNull(message = "Can score permission cannot be empty!")
    private Boolean canScoreKeep;
}