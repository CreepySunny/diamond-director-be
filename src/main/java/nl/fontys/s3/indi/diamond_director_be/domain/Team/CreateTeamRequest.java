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
public class CreateTeamRequest {
    @NotBlank(message = "Coach email must not be blank")
    private String createCoachUserEmail;

    @NotBlank(message = "Team name must not be blank")
    private String teamName;
}
