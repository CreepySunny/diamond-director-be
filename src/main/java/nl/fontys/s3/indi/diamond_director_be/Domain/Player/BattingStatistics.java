package nl.fontys.s3.indi.diamond_director_be.Domain.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BattingStatistics {
    private Integer hits;
    private Integer walks;
    private Integer singles;
    private Integer doubles;
    private Integer triples;
    private Integer homeRuns;
    private Integer outs;

    private Double battingAverage;
    private Double onBasePercentage;
    private Double sluggingPercentage;
    private Double onBasePlusSlugging;
    private Double weightedOnBaseAverage;
}
