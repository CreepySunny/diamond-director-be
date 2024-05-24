package nl.fontys.s3.indi.diamond_director_be.domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineUpCardEntry {
    private int lineUpPosition;
    private PlayerPosition fieldingPosition;
    private Player player;
}
