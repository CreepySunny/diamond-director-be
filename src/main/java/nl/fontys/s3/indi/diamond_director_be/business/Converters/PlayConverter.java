package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;

public final class PlayConverter {
    private PlayConverter() {}

    public static Play convert(PlayEntity playEntity) {
        return Play.builder()
                .inning(playEntity.getInning())
                .half(playEntity.getHalf())
                .playResult(playEntity.getPlayResult())
                .batter(PlayerConverter.convert(playEntity.getBatter()))
                .pitcher(PlayerConverter.convert(playEntity.getPitcher()))
                .build();
    }
}
