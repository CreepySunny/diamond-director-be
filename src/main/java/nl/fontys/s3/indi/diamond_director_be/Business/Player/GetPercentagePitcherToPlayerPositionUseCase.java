package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPositionPitcherStatisticsResponse;

public interface GetPercentagePitcherToPlayerPositionUseCase {
    PlayerPositionPitcherStatisticsResponse getPercentageForPitcher(Long playerUserId, PlayerPosition position);
}
