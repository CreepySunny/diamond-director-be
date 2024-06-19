package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.CalculatePitchingStatisticsUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PitchingStatistics;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatePitchingStatisticsUseCaseImplTest {

    @InjectMocks
    private CalculatePitchingStatisticsUseCaseImpl calculatePitchingUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculatePitchingStatistics() {
        List<Play> plays = Arrays.asList(
                createPlay(PlayResult.SINGLE),
                createPlay(PlayResult.DOUBLE),
                createPlay(PlayResult.TRIPLE),
                createPlay(PlayResult.HOME_RUN),
                createPlay(PlayResult.STRIKEOUT),
                createPlay(PlayResult.WALK),
                createPlay(PlayResult.NON_INTENTIONAL_WALK),
                createPlay(PlayResult.INTENTIONAL_WALK),
                createPlay(PlayResult.HIT_BY_PITCH),
                createPlay(PlayResult.SACRIFICE_FLY)
        );

        PitchingStatistics result = calculatePitchingUseCase.calculatePitchingStatistics(plays);

        assertNotNull(result);
        assertEquals(4, result.getHits());
        assertEquals(3, result.getWalks());
        assertEquals(1, result.getSingles());
        assertEquals(1, result.getDoubles());
        assertEquals(1, result.getTriples());
        assertEquals(1, result.getHomeRuns());
        assertEquals(1, result.getStrikeOuts());

        assertEquals(0.75, result.getBabip());
    }

    @Test
    void testCalculatePitchingStatistics_NoPlays() {
        List<Play> plays = Arrays.asList();

        PitchingStatistics result = calculatePitchingUseCase.calculatePitchingStatistics(plays);

        assertNotNull(result);
        assertEquals(0, result.getHits());
        assertEquals(0, result.getWalks());
        assertEquals(0, result.getSingles());
        assertEquals(0, result.getDoubles());
        assertEquals(0, result.getTriples());
        assertEquals(0, result.getHomeRuns());
        assertEquals(0, result.getStrikeOuts());

        assertEquals(0.0, result.getBabip());
    }

    private Play createPlay(PlayResult playResult) {
        return new Play(1, InningHalves.TOP, new Player(), new Player(), playResult, List.of(), 1);
    }
}