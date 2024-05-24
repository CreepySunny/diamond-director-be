package nl.fontys.s3.indi.diamond_director_be.domain.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.ArrayList;
import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Play {
    private Player batter;
    private PlayResult playResult;
    private ArrayList<Player> fielders;
    private Map<Bases, Player> baseRunners;
    private Integer rbi;
}
