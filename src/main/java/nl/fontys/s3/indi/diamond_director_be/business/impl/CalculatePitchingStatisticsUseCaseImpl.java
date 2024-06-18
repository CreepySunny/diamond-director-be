package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CalculatePitchingStatisticsUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PitchingStatistics;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CalculatePitchingStatisticsUseCaseImpl implements CalculatePitchingStatisticsUseCase {
    private Integer atBats = 0;
    private Integer singles = 0;
    private Integer doubles = 0;
    private Integer triples = 0;
    private Integer homeRuns = 0;
    private Integer hits = 0;
    private Integer outs = 0;
    private Integer walks = 0;
    private Integer hitByPitch = 0;
    private Integer sacFly = 0;
    private Integer strikeOuts = 0;

    @Override
    public PitchingStatistics calculatePitchingStatistics(List<Play> plays) {

        for (Play play : plays){
            countStats(play.getPlayResult());
        }

        double babip = BaseballStatisticsService.calculateBABIP(hits, homeRuns, atBats, strikeOuts, sacFly);
        double inningsPitched = (double) outs /3;
        double fip = BaseballStatisticsService.calculateFIP(homeRuns, walks, hitByPitch, strikeOuts,inningsPitched);
        double whip = BaseballStatisticsService.calculateWHIP(walks, hits, inningsPitched);
        double k9 = BaseballStatisticsService.calculateKPer9(strikeOuts, inningsPitched);
        double bb9 = BaseballStatisticsService.calculateBBPer9(walks, inningsPitched);
        double hr9 = BaseballStatisticsService.calculateHRPer9(homeRuns, inningsPitched);
        return PitchingStatistics.builder()
                .hits(hits)
                .walks(walks)
                .singles(singles)
                .doubles(doubles)
                .triples(triples)
                .homeRuns(homeRuns)
                .strikeOuts(strikeOuts)
                .babip(babip)
                .fip(fip)
                .whip(whip)
                .k9(k9)
                .bb9(bb9)
                .hr9(hr9)
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
                strikeOuts++;
            case GROUNDOUT:
            case FLYOUT:
            case LINEOUT:
            case POP_OUT:
                outs++;
                atBats++;
                break;
            case WALK, NON_INTENTIONAL_WALK, INTENTIONAL_WALK:
                walks++;
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
