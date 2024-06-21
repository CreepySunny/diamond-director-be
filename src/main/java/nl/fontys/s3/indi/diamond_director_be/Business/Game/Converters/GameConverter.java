package nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters;

import nl.fontys.s3.indi.diamond_director_be.Business.Team.Converters.TeamConverter;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;

import java.util.HashMap;

public final class GameConverter {
    private GameConverter() {
    }

    public static Game convert(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .season(gameEntity.getSeason())
                .homeScore(gameEntity.getHomeScore())
                .away(TeamConverter.convert(gameEntity.getAwayTeam()))
                .home(TeamConverter.convert(gameEntity.getHomeTeam()))
                .awayScore(gameEntity.getAwayScore())
                .outs(gameEntity.getOuts())
                .currentHalf(gameEntity.getHalves())
                .inning(gameEntity.getInning())
                .playsInAGame(gameEntity.getPlays().stream()
                        .map(PlayConverter::convert)
                        .toList())
                .baseRunners(new HashMap<>())
                .build();
    }

    public static GameEntity convert(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .season(game.getSeason())
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .halves(game.getCurrentHalf())
                .outs(game.getOuts())
                .inning(game.getInning())
                .build();
    }
}
