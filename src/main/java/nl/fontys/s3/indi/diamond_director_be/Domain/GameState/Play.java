package nl.fontys.s3.indi.diamond_director_be.Domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Play {
    private Integer inning;
    private InningHalves half;
    private Player batter;
    private Player pitcher;
    private PlayResult playResult;
    private List<Long> fielders;
    private Integer rbi;
}
