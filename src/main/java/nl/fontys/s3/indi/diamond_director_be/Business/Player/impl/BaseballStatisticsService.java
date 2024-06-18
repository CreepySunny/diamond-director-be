package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

public class BaseballStatisticsService {
    private static final double WOBA_WEIGHT_NIBB = 0.697;
    private static final double WOBA_WEIGHT_HBP = 0.727;
    private static final double WOBA_WEIGHT_1B = 0.855;
    private static final double WOBA_WEIGHT_2B = 1.248;
    private static final double WOBA_WEIGHT_3B = 1.575;
    private static final double WOBA_WEIGHT_HR = 2.014;

    private static final double FIP_CONSTANT = 3.214;

    public static double calculateAVG(int hits, int atBats) {
        if (atBats == 0) return 0;
        return (double) hits / atBats;
    }

    public static double calculateOBP(int hits, int walks, int hitByPitch, int atBats, int sacFlies) {
        int denominator = atBats + walks + hitByPitch + sacFlies;
        if (denominator == 0) return 0;
        double obp = (double) (hits + walks + hitByPitch) / denominator;
        return Math.round(obp * 1000.0) / 1000.0;
    }

    public static double calculateSLG(int singles, int doubles, int triples, int homeRuns, int atBats) {
        if (atBats == 0) return 0;
        double totalBases = singles + (2 * doubles) + (3 * triples) + (4 * homeRuns);
        return totalBases / atBats;
    }

    public static double calculateOPS(double OBP, double SLG) {
        return (OBP + SLG)/2;
    }

    public static double calculateWOBA(int NIBB, int HBP, int singles, int doubles, int triples, int homeRuns,
                                       int atBats, int walks, int intentionalWalks, int sacFlies) {
        if (atBats + walks + intentionalWalks + sacFlies == 0) return 0;
        double wOBA = ((WOBA_WEIGHT_NIBB * NIBB) + (WOBA_WEIGHT_HBP * HBP) + (WOBA_WEIGHT_1B * singles) +
                (WOBA_WEIGHT_2B * doubles) + (WOBA_WEIGHT_3B * triples) + (WOBA_WEIGHT_HR * homeRuns)) /
                (atBats + walks + intentionalWalks + sacFlies + HBP);
        return Math.round(wOBA * 1000.0) / 1000.0;
    }

    public static double calculateBABIP(int hits, int homeRuns, int atBats, int strikeouts, int sacFlies) {
        int ballsInPlay = hits - homeRuns;
        int denominator = atBats - homeRuns - strikeouts + sacFlies;
        if (denominator == 0) return 0;
        double babip = (double) ballsInPlay / denominator;
        return Math.round(babip * 1000.0) / 1000.0;
    }

    public static double calculateFIP(int homeRuns, int walks, int hitByPitch, int strikeouts, double inningsPitched) {
        double fip = ((13 * homeRuns) + (3 * (walks + hitByPitch)) - (2 * strikeouts)) / inningsPitched + FIP_CONSTANT;
        return Math.round(fip * 100.0) / 100.0;
    }

    public static double calculateERA(int earnedRuns, double inningsPitched) {
        if (inningsPitched == 0) return 0;
        return (earnedRuns * 9) / inningsPitched;
    }

    public static double calculateWHIP(int walks, int hits, double inningsPitched) {
        if (inningsPitched == 0) return 0;
        return (walks + hits) / inningsPitched;
    }

    public static double calculateKPer9(int strikeouts, double inningsPitched) {
        if (inningsPitched == 0) return 0;
        return (strikeouts * 9) / inningsPitched;
    }

    public static double calculateBBPer9(int walks, double inningsPitched) {
        if (inningsPitched == 0) return 0;
        return (walks * 9) / inningsPitched;
    }

    public static double calculateHRPer9(int homeRuns, double inningsPitched) {
        if (inningsPitched == 0) return 0;
        return (homeRuns * 9) / inningsPitched;
    }
}
