package nl.fontys.s3.indi.diamond_director_be.Domain.Player;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPositionPitcherStatisticsResponse {
    private Double percentageToPlayerPosition;
    private PlayerPosition position;

    private Double babip;
    private Double fip;
    private Double whip;
    private Double k9;
    private Double bb9;
    private Double hr9;
}
