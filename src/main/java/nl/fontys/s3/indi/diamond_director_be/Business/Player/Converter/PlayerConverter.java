package nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;

public final class PlayerConverter {
    private PlayerConverter() {}


    public static PlayerEntity convert(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setFirstName(player.getFirstName());
        playerEntity.setLastName(player.getLastName());
        playerEntity.setHandedBats(player.getHanded_bats());
        playerEntity.setHandedThrows(player.getHanded_throws());
        playerEntity.setPosition(player.getPosition());
        playerEntity.setDateOfBirth(player.getDateOfBirth());
        playerEntity.setHeight(player.getHeight());
        playerEntity.setWeight(player.getWeight());
        return playerEntity;
    }
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
