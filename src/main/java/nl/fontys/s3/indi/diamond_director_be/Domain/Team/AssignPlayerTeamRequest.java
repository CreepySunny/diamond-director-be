package nl.fontys.s3.indi.diamond_director_be.Domain.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AssignPlayerTeamRequest {
    @NotBlank
    private String teamName;

    @NotNull
    private Long playerId;
}
