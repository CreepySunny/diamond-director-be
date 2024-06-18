package nl.fontys.s3.indi.diamond_director_be.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPositionBatterStatisticsResponse {
    private Double percentageToPlayerPosition;
    private PlayerPosition position;

    private Double battingAverage;
    private Double onBasePercentage;
    private Double sluggingPercentage;
    private Double onBasePlusSlugging;
    private Double weightedOnBaseAverage;
}