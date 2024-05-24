package nl.fontys.s3.indi.diamond_director_be.domain.GameState;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Game {
    private Long id;
    private LineUpCard homeLineUp, awayLineUp;
    private Integer homeScore, awayScore;
    private List<Play> playsInAGame;
}
