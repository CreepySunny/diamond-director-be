package nl.fontys.s3.indi.diamond_director_be.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;
import java.util.Map;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Game {
    private Team homeTeam, awayTeam;
    private Integer homeScore, awayScore;

    private Map<Player, Bases> baseRunnerPosition;
    private List<Play> playsInAGame;
}
