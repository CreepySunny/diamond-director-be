package nl.fontys.s3.indi.diamond_director_be.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;

import java.time.LocalDate;
import java.util.Date;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Player extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private String handed_bats;
    private String handed_throws;
    private Position position;
    private LocalDate dateOfBirth;
    private Double height;
    private Double weight;
}
