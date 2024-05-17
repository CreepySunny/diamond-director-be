package nl.fontys.s3.indi.diamond_director_be.domain.Coach;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserRequest;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateCoachRequest extends CreateUserRequest {

    @NotNull
    private LocalDate dateOfBirth;


    @NotNull
    private CoachPosition position;

    @NotNull
    private Boolean canScoreKeep;
}
