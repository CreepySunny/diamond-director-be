package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;

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
}
