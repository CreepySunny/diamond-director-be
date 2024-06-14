package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayFielderEntity;

public class PlayFielderConverter {
    private PlayFielderConverter() {}

    public static PlayerPosition convert(PlayFielderEntity playFielderEntities) {
        return playFielderEntities.getFielder();
    }

    public static PlayFielderEntity convert(PlayerPosition playFieldPosition) {
        return PlayFielderEntity.builder().fielder(playFieldPosition).build();
    }
}
