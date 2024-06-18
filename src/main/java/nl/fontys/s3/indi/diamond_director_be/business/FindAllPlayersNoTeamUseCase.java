package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;

public interface FindAllPlayersNoTeamUseCase {
    List<Player> findPlayersNoTeam();
}
