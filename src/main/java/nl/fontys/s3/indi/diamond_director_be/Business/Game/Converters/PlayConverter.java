package nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters;

import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Play;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayEntity;

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
