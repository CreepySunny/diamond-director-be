package nl.fontys.s3.indi.diamond_director_be.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreatePlayerRequest extends CreateUserRequest {
    @NotBlank
    @Length(min = 2, max = 50)
    private String handed_bats;

    @NotBlank
    @Length(min = 2, max = 50)
    private String handed_throws;

    @NotNull
    private Position position;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Double height;
    @NotNull
    private Double weight;
}
