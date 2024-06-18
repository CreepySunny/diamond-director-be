package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPositionBatterStatisticsResponse;

public interface GetPercentageBatterToPlayerPositionUseCase {
    PlayerPositionBatterStatisticsResponse getPerPositionStats(Long playerUserId, PlayerPosition position);
}
