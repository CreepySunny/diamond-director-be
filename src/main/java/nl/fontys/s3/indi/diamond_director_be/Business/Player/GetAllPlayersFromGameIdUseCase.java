package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;

import java.util.List;

public interface GetAllPlayersFromGameIdUseCase {
    List<Player> getAllPlayersFromGameId(Long gameId);
}
