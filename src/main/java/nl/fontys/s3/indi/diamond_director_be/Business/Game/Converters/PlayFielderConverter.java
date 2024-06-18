package nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayFielderEntity;

public class PlayFielderConverter {
    private PlayFielderConverter() {}

    public static PlayerPosition convert(PlayFielderEntity playFielderEntities) {
        return playFielderEntities.getFielder();
    }

    public static PlayFielderEntity convert(PlayerPosition playFieldPosition) {
        return PlayFielderEntity.builder().fielder(playFieldPosition).build();
    }
}
