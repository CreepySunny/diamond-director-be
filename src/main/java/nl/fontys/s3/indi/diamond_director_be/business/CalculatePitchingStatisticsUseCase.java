package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PitchingStatistics;

import java.util.List;

public interface CalculatePitchingStatisticsUseCase {
    PitchingStatistics calculatePitchingStatistics(List<Play> plays);
}
