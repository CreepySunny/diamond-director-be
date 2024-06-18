package nl.fontys.s3.indi.diamond_director_be.domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {
    private Long gameId;
    private String season;
    private String awayTeamName;
    private String homeTeamName;
}