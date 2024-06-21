package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PitchingStatistics;

import java.util.List;

public interface CalculatePitcherStatisticsFromPlays {
    PitchingStatistics calculatePitching(List<Play> plays);
}
