package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

public interface FindPlayerByUserIdUseCase {
    Player findPlayerByUserId(Long userId);
}
