package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.GetAllPlayersFromGameIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class GetAllPlayersFromGameIdUseCaseImpl implements GetAllPlayersFromGameIdUseCase {
    private final GameRepository gameRepository;
    @Override
    public List<Player> getAllPlayersFromGameId(Long gameId) {
        GameEntity foundGame = gameRepository.findById(gameId).orElseThrow(NO_GAME_EXCEPTION::new);
        return Stream.concat(
                foundGame.getHomeTeam().getPlayers()
                        .stream()
                        .map(PlayerConverter::convert),
                foundGame.getAwayTeam().getPlayers()
                        .stream()
                        .map(PlayerConverter::convert))
                .toList();
    }
}
