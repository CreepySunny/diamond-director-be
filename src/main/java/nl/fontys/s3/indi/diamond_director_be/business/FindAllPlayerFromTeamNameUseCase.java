package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;

public interface FindAllPlayerFromTeamNameUseCase {
    List<Player> findPlayersFromTeamName(String teamName);
}
