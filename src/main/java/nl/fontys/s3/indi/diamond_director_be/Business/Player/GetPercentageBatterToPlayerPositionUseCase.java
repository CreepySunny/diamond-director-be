package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPositionBatterStatisticsResponse;

public interface GetPercentageBatterToPlayerPositionUseCase {
    PlayerPositionBatterStatisticsResponse getPerPositionStats(Long playerUserId, PlayerPosition position);
}
