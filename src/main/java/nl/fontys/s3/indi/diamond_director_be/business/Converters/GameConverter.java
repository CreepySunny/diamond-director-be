package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;

import java.util.stream.Collectors;

public final class GameConverter {
    private GameConverter() {}

    public static Game convert(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .season(gameEntity.getSeason())
                .homeScore(gameEntity.getHomeScore())
                .awayScore(gameEntity.getAwayScore())
                .outs(gameEntity.getOuts())
                .inning(gameEntity.getInning())
                .playsInAGame(gameEntity.getPlays().stream()
                        .map(PlayConverter::convert)
                        .toList())
                .build();
    }

    public static GameEntity convert(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .season(game.getSeason())
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .outs(game.getOuts())
                .inning(game.getInning())
                .build();
    }
}
