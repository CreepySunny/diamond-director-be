package nl.fontys.s3.indi.diamond_director_be.domain.Game;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.domain.Team;

import java.util.List;
import java.util.Map;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Game {
    private Team homeTeam, awayTeam;
    private Integer homeScore, awayScore;
    private List<Play> playsInAGame;
}
