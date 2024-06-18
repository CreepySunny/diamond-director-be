package nl.fontys.s3.indi.diamond_director_be.Domain.GameState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreRequest {
    @NotNull
    private Integer inning;

    @NotNull
    private InningHalves half;

    @NotNull
    private Long gameId;

    @NotNull
    private Long batterId;

    @NotNull
    private Long pitcherId;

    @NotBlank
    private String playShorthand;

    @NotNull
    private List<PlayerPosition> fieldersPositions;
}
