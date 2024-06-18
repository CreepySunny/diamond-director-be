package nl.fontys.s3.indi.diamond_director_be.business.impl;


import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CalculateBattingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.GetPercentageBatterToPlayerPositionUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.BattingStatistics;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPositionBatterStatisticsResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GetPercentageBatterToPlayerPositionUseCaseImpl implements GetPercentageBatterToPlayerPositionUseCase {
    private final PlayRepository playRepository;
    private final PlayerRepository playerRepository;
    private final CalculateBattingStatisticsUseCase calculateBattingStatisticsUseCase;


    @Override
    public PlayerPositionBatterStatisticsResponse getPerPositionStats(Long playerUserId, PlayerPosition position) {
        PlayerEntity foundBatter = playerRepository.findByUserEntityId(playerUserId).orElseThrow(NO_PLAYER_EXCEPTION::new);

        Long countPerPositionAndBatter = playRepository.countPlaysByBatterAndFielderPosition(foundBatter, position);
        Long countAllPlaysPerBatter = playRepository.countPlaysByBatter(foundBatter);
        List<Play>  playsPerPositionAndBatter = playRepository.findPlaysByBatterAndFielderPosition(foundBatter, position)
                .stream()
                .map(PlayConverter::convert)
                .toList();

        BattingStatistics battingStatistics = calculateBattingStatisticsUseCase.calculateBatting(playsPerPositionAndBatter);


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