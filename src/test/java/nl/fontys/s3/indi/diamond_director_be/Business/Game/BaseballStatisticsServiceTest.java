package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.impl.BaseballStatisticsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseballStatisticsServiceTest {
    @Test
    void testCalculateAVG() {
        assertEquals(0.333, BaseballStatisticsService.calculateAVG(10, 30), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateAVG(0, 0));
    }

    @Test
    void testCalculateOBP() {
        assertEquals(0.421, BaseballStatisticsService.calculateOBP(10, 5, 1, 30, 2), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateOBP(0, 0, 0, 0, 0));
    }

    @Test
    void testCalculateSLG() {
        assertEquals(0.750, BaseballStatisticsService.calculateSLG(10, 5, 2, 1, 40), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateSLG(0, 0, 0, 0, 0));
    }

    @Test
    void testCalculateOPS() {
        assertEquals(0.250, BaseballStatisticsService.calculateOPS(0.250, 0.250), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateOPS(0, 0));
    }

    @Test
    void testCalculateWOBA() {
        assertEquals(0.797, BaseballStatisticsService.calculateWOBA(10, 5, 5, 5, 5, 5, 30, 10, 2, 2), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateWOBA(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    void testCalculateBABIP() {
        assertEquals(0.381, BaseballStatisticsService.calculateBABIP(9, 1, 30, 10, 2), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateBABIP(0, 0, 0, 0, 0));
    }

    @Test
    void testCalculateFIP() {
        assertEquals(5.37, BaseballStatisticsService.calculateFIP(10, 5, 1, 20, 50), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateFIP(0, 0, 0, 0, 0));
    }

    @Test
    void testCalculateERA() {
        assertEquals(2.25, BaseballStatisticsService.calculateERA(4, 16.0), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateERA(0, 0));
    }

    @Test
    void testCalculateWHIP() {
        assertEquals(1.875, BaseballStatisticsService.calculateWHIP(5, 10, 8.0), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateWHIP(0, 0, 0));
    }

    @Test
    void testCalculateKPer9() {
        assertEquals(9.0, BaseballStatisticsService.calculateKPer9(27, 27.0), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateKPer9(0, 0));
    }

    @Test
    void testCalculateBBPer9() {
        assertEquals(3.375, BaseballStatisticsService.calculateBBPer9(9, 24.0), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateBBPer9(0, 0));
    }

    @Test
    void testCalculateHRPer9() {
        assertEquals(1.0, BaseballStatisticsService.calculateHRPer9(3, 27.0), 0.001);
        assertEquals(0, BaseballStatisticsService.calculateHRPer9(0, 0));
    }

}