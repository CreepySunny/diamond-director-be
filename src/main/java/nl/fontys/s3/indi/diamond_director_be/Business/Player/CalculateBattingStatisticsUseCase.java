package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.BattingStatistics;

import java.util.List;

public interface CalculateBattingStatisticsUseCase {
    BattingStatistics calculateBatting(List<Play> plays);
}
