package nl.fontys.s3.indi.diamond_director_be.domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.InningHalves;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreRequest {
    private Integer inning;
    private InningHalves half;
    private Long gameId;
    private Long batterId;
    private Long pitcherId;
    private String playShorthand;
    private List<Long> fieldersPositions;
}
