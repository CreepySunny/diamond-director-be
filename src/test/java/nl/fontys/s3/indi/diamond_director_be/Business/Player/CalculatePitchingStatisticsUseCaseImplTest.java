package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.CalculatePitchingStatisticsUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PitchingStatistics;
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

import static org.junit.jupiter.api.Assertions.*;

public class CalculatePitchingStatisticsUseCaseImplTest {

    @InjectMocks
    private CalculatePitchingStatisticsUseCaseImpl calculatePitchingUseCase;

    @Mock
    private PlayRepository playRepository;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculatePitchingStatistics() {
        Long pitcherId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        List<PlayEntity> playEntities = Arrays.asList(
                createPlayE(PlayResult.SINGLE),
                createPlayE(PlayResult.DOUBLE),
                createPlayE(PlayResult.TRIPLE),
                createPlayE(PlayResult.HOME_RUN),
                createPlayE(PlayResult.STRIKEOUT),
                createPlayE(PlayResult.WALK),
                createPlayE(PlayResult.NON_INTENTIONAL_WALK),
                createPlayE(PlayResult.INTENTIONAL_WALK),
                createPlayE(PlayResult.HIT_BY_PITCH),
                createPlayE(PlayResult.SACRIFICE_FLY)
        );

        Mockito.when(playerRepository.findById(pitcherId)).thenReturn(Optional.of(playerEntity));
        Mockito.when(playRepository.findByPitcher(playerEntity)).thenReturn(playEntities);

        PitchingStatistics result = calculatePitchingUseCase.calculatePitchingStatistics(pitcherId);

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
        Long pitcherId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();

        Mockito.when(playerRepository.findById(pitcherId)).thenReturn(Optional.of(playerEntity));
        Mockito.when(playRepository.findByPitcher(playerEntity)).thenReturn(List.of());

        PitchingStatistics result = calculatePitchingUseCase.calculatePitchingStatistics(pitcherId);

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