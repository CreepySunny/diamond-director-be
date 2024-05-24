package nl.fontys.s3.indi.diamond_director_be.domain.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineUpCard {
    private Long id;
    private String teamName;
    private List<LineUpCardEntry> lineUpCardEntries;
    private List<Player> substitutions;
}
