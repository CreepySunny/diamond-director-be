package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;


import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CalculateBatterStatisticsFromPlays;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CalculateBattingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.GetPercentageBatterToPlayerPositionUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.BattingStatistics;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPositionBatterStatisticsResponse;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GetPercentageBatterToPlayerPositionUseCaseImpl implements GetPercentageBatterToPlayerPositionUseCase {
    private final PlayRepository playRepository;
    private final PlayerRepository playerRepository;
    private final CalculateBatterStatisticsFromPlays calculateBatterStatisticsFromPlays;


    @Override
    public PlayerPositionBatterStatisticsResponse getPerPositionStats(Long playerUserId, PlayerPosition position) {
        PlayerEntity foundBatter = playerRepository.findByUserEntityId(playerUserId).orElseThrow(NO_PLAYER_EXCEPTION::new);

        Long countPerPositionAndBatter = playRepository.countPlaysByBatterAndFielderPosition(foundBatter, position);
        Long countAllPlaysPerBatter = playRepository.countPlaysByBatter(foundBatter);
        List<Play>  playsPerPositionAndBatter = playRepository.findPlaysByBatterAndFielderPosition(foundBatter, position)
                .stream()
                .map(PlayConverter::convert)
                .toList();

        BattingStatistics battingStatistics = calculateBatterStatisticsFromPlays.calculateBatterStatistics(playsPerPositionAndBatter);


        return PlayerPositionBatterStatisticsResponse.builder()
                .percentageToPlayerPosition((double) ((countPerPositionAndBatter/countAllPlaysPerBatter)*100))
                .weightedOnBaseAverage(battingStatistics.getWeightedOnBaseAverage())
                .battingAverage(battingStatistics.getBattingAverage())
                .onBasePercentage(battingStatistics.getOnBasePercentage())
                .sluggingPercentage(battingStatistics.getSluggingPercentage())
                .onBasePlusSlugging(battingStatistics.getOnBasePlusSlugging())
                .position(position)
                .build();
    }
}