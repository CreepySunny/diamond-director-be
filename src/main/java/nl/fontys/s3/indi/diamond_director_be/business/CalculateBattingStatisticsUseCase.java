package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.BattingStatistics;

import java.util.List;

public interface CalculateBattingStatisticsUseCase {
    BattingStatistics calculateBatting(List<Play> plays);
}
