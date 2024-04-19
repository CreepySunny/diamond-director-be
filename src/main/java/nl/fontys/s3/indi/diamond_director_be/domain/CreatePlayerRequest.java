package nl.fontys.s3.indi.diamond_director_be.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerRequest {
    @NotBlank
    @Length(min = 2, max = 255)
    private String firstName;
    @NotBlank
    @Length(min = 2, max = 255)
    private String lastName;
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
