package nl.fontys.s3.indi.diamond_director_be.domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreRequest {
    private Long gameId;
    private Long batterId;
    private String playShorthand;
    private List<Long> fieldersPlayerIds;
    private Map<Bases, Player> baseRunners;
    private Integer rbi;
}
