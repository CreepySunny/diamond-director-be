package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;

public final class PlayerConverter {
    private PlayerConverter() {}

    public static Player convert(PlayerEntity playerEntity) {
        return Player.builder()
                .id(playerEntity.getId())
                .firstName(playerEntity.getFirstName())
                .lastName(playerEntity.getLastName())
                .handed_bats(playerEntity.getHandedBats())
                .handed_throws(playerEntity.getHandedThrows())
                .position(playerEntity.getPosition())
                .dateOfBirth(playerEntity.getDateOfBirth())
                .height(playerEntity.getHeight())
                .weight(playerEntity.getWeight())
                .build();
    }
}
