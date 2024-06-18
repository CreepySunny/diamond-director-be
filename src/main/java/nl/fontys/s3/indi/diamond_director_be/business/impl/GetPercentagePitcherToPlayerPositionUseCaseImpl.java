package nl.fontys.s3.indi.diamond_director_be.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CalculatePitchingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.GetPercentagePitcherToPlayerPositionUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.*;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPercentagePitcherToPlayerPositionUseCaseImpl implements GetPercentagePitcherToPlayerPositionUseCase {
    private final PlayRepository playRepository;
    private final PlayerRepository playerRepository;
    private final CalculatePitchingStatisticsUseCase calculatePitchingStatisticsUseCase;


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

        PitchingStatistics pitchingStatistics = calculatePitchingStatisticsUseCase.calculatePitchingStatistics(playsPerFieldPositionAndPitcher);

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
