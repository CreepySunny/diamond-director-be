package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CalculatePitcherStatisticsFromPlays;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CalculatePitchingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.GetPercentagePitcherToPlayerPositionUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.*;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPercentagePitcherToPlayerPositionUseCaseImpl implements GetPercentagePitcherToPlayerPositionUseCase {
    private final PlayRepository playRepository;
    private final PlayerRepository playerRepository;
    private final CalculatePitcherStatisticsFromPlays calculatePitchingStatistics;


    @Override
    @Transactional
    public PlayerPositionPitcherStatisticsResponse getPercentageForPitcher(Long playerUserId, PlayerPosition position) {
        PlayerEntity foundPitcher = playerRepository.findByUserEntityId(playerUserId).orElseThrow(NO_PLAYER_EXCEPTION::new);

        Long countPerFieldPositionPerPitcher = playRepository.countPlaysByPitcherAndFielderPosition(foundPitcher, position);
        Long countAllPlaysPerPitcher = playRepository.countPlaysByPitcher(foundPitcher);

        List<Play> playsPerFieldPositionAndPitcher = playRepository.findPlaysByPitcherAndFielderPosition(foundPitcher, position)
                .stream()
                .map(PlayConverter::convert)
                .toList();

        PitchingStatistics pitchingStatistics = calculatePitchingStatistics.calculatePitching(playsPerFieldPositionAndPitcher);

        return PlayerPositionPitcherStatisticsResponse.builder()
                .percentageToPlayerPosition((double)((countPerFieldPositionPerPitcher/countAllPlaysPerPitcher)*100))
                .position(position)
                .k9(pitchingStatistics.getK9())
                .babip(pitchingStatistics.getBabip())
                .bb9(pitchingStatistics.getBb9())
                .fip(pitchingStatistics.getFip())
                .hr9(pitchingStatistics.getHr9())
                .whip(pitchingStatistics.getWhip())
                .build();
    }
}
