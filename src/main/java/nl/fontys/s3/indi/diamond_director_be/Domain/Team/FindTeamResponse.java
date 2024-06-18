package nl.fontys.s3.indi.diamond_director_be.Domain.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.HttpStatusCodeException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindTeamResponse {
    private HttpStatusCodeException exception;
    private Team team;
}
