package nl.fontys.s3.indi.diamond_director_be.Domain.GameState;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateGameRequest {
    @NotBlank
    String season;

    @NotBlank
    String homeTeamName;

    @NotBlank
    String awayTeamName;
}
