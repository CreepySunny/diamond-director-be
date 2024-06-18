package nl.fontys.s3.indi.diamond_director_be.Domain.Player;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserRequest;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreatePlayerRequest extends CreateUserRequest {
    @NotNull
    private PlayerHanded handed_bats;

    @NotNull
    private PlayerHanded handed_throws;

    @NotNull
    private PlayerPosition position;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Double height;
    @NotNull
    private Double weight;
}
