package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;

public interface FindPlayerByUserIdUseCase {
    Player findPlayerByUserId(Long userId);
}
