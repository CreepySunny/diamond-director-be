package nl.fontys.s3.indi.diamond_director_be.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PitchingStatistics {
    private Integer hits;
    private Integer walks;
    private Integer singles;
    private Integer doubles;
    private Integer triples;
    private Integer homeRuns;
    private Integer strikeOuts;

    private Double babip;
    private Double fip;
    private Double whip;
    private Double k9;
    private Double bb9;
    private Double hr9;
}
