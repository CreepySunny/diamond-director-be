package nl.fontys.s3.indi.diamond_director_be.domain.Team;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignCoachTeamRequest {
    @NotBlank
    private String teamName;

    @NotBlank
    private String coachEmail;
}
