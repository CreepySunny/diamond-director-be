package nl.fontys.s3.indi.diamond_director_be.Domain.GameState;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private Long id;
    private String season;
    private Integer homeScore = 0;
    private Integer awayScore = 0;
    private Integer outs = 0;
    private Integer inning = 1;
    private InningHalves currentHalf = InningHalves.TOP;
    private Team home;
    private Team away;
    private Map<Bases, Player> baseRunners = new HashMap<>();
    private List<Play> playsInAGame = new ArrayList<>();
}