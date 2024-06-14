package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.business.CalculateBattingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.BattingStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateBattingStatisticsUseCaseImpl implements CalculateBattingStatisticsUseCase{
    private Integer atBats = 0;
    private Integer singles = 0;
    private Integer doubles = 0;
    private Integer triples = 0;
    private Integer homeRuns = 0;
    private Integer hits = 0;
    private Integer outs = 0;
    private Integer nonIntWalk = 0;
    private Integer intWalk = 0;
    private Integer walks = 0;
    private Integer hitByPitch = 0;
    private Integer sacFly = 0;

    @Override
    public BattingStatistics calculateBatting(List<Play> plays) {
        for (Play play : plays){
            countStats(play.getPlayResult());
        }
        double obp = BaseballStatisticsService.calculateOBP(hits, walks, hitByPitch, atBats, sacFly);
        double slg = BaseballStatisticsService.calculateSLG(singles, doubles, triples, homeRuns, atBats);
        Double avg = BaseballStatisticsService.calculateAVG(hits, atBats);
        Double wOBA = BaseballStatisticsService.calculateWOBA(nonIntWalk, hitByPitch, singles, doubles, triples, homeRuns, atBats, walks, intWalk, sacFly);
         return BattingStatistics.builder()
                .hits(hits)
                .walks(walks)
                .singles(singles)
                .doubles(doubles)
                .triples(triples)
                .homeRuns(homeRuns)
                .outs(outs)
                .battingAverage(avg)
                .onBasePercentage(obp)
                .sluggingPercentage(slg)
                .onBasePlusSlugging(BaseballStatisticsService.calculateOPS(obp, slg))
                .weightedOnBaseAverage(wOBA)
                .build();
    }

    private void countStats(PlayResult playResult){
        switch (playResult) {
            case SINGLE:
                singles++;
                hits++;
                atBats++;
                break;
            case DOUBLE:
                doubles++;
                hits++;
                atBats++;
                break;
            case TRIPLE:
                triples++;
                hits++;
                atBats++;
                break;
            case HOME_RUN:
                homeRuns++;
                hits++;
                atBats++;
                break;
            case STRIKEOUT:
            case GROUNDOUT:
            case FLYOUT:
            case LINEOUT:
            case POP_OUT:
                outs++;
                atBats++;
                break;
            case WALK:
            case NON_INTENTIONAL_WALK:
                walks++;
                nonIntWalk++;
                break;
            case INTENTIONAL_WALK:
                walks++;
                intWalk++;
                break;
            case HIT_BY_PITCH:
                hitByPitch++;
                break;
            case SACRIFICE_FLY:
                sacFly++;
                break;
        }

    }
}
