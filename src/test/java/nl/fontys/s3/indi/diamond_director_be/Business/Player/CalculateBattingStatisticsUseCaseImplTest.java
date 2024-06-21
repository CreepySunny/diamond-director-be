package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import static org.junit.jupiter.api.Assertions.*;


import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.CalculateBattingStatisticsUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.BattingStatistics;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class CalculateBattingStatisticsUseCaseImplTest {

    @InjectMocks
    private CalculateBattingStatisticsUseCaseImpl calculateBattingUseCase;

    @Mock
    private PlayRepository playRepository;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateBattingStatistics() {
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

        BattingStatistics result = calculateBattingUseCase.calculateBatterStatistics(plays);

        assertNotNull(result);
        assertEquals(4, result.getHits());
        assertEquals(3, result.getWalks());
        assertEquals(1, result.getSingles());
        assertEquals(1, result.getDoubles());
        assertEquals(1, result.getTriples());
        assertEquals(1, result.getHomeRuns());
        assertEquals(1, result.getOuts());
        assertEquals(0.8, result.getBattingAverage());
        assertEquals(0.8, result.getOnBasePercentage());
        assertEquals(2.0, result.getSluggingPercentage());
        assertEquals(1.4, result.getOnBasePlusSlugging());
        assertEquals(0.71, result.getWeightedOnBaseAverage(), 0.001);
    }

    @Test
    void testCalculateBattingStatistics_NoPlays() {
        List<Play> plays = Arrays.asList();

        BattingStatistics result = calculateBattingUseCase.calculateBatterStatistics(plays);

        assertNotNull(result);
        assertEquals(0, result.getHits());
        assertEquals(0, result.getWalks());
        assertEquals(0, result.getSingles());
        assertEquals(0, result.getDoubles());
        assertEquals(0, result.getTriples());
        assertEquals(0, result.getHomeRuns());
        assertEquals(0, result.getOuts());
    }

    @Test
    void testCalculateBattingStatistics_WithBatterId() {
        Long batterId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        List<PlayEntity> playEntities = Arrays.asList(
                createPlayE(PlayResult.SINGLE),
                createPlayE(PlayResult.DOUBLE),
                createPlayE(PlayResult.TRIPLE)
        );

        Mockito.when(playerRepository.findById(batterId)).thenReturn(Optional.of(playerEntity));
        Mockito.when(playRepository.findByBatter(playerEntity)).thenReturn(playEntities);

        BattingStatistics result = calculateBattingUseCase.calculateBatting(batterId);

        assertNotNull(result);
        assertEquals(3, result.getHits());
        assertEquals(1, result.getSingles());
        assertEquals(1, result.getDoubles());
        assertEquals(1, result.getTriples());
    }

    private Play createPlay(PlayResult playResult) {
        return new Play(1, InningHalves.TOP, new Player(), new Player(), playResult, List.of(), 1);
    }
    private PlayEntity createPlayE(PlayResult playResult) {
        return PlayEntity.builder()
                .id(1L)
                .half(InningHalves.BOTTOM)
                .inning(1)
                .playResult(playResult)
                .fielders(List.of())
                .batter(PlayerEntity.builder().build())
                .pitcher(PlayerEntity.builder().build())
                .build();
    }
}