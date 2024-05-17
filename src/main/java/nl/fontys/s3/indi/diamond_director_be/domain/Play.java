package nl.fontys.s3.indi.diamond_director_be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Play {
    private PlayResult result;
    private Player batter;

    private Bases batterRunnerPosition;
    @Builder.Default
    private Bases existingRunnerPosition = null;
}
