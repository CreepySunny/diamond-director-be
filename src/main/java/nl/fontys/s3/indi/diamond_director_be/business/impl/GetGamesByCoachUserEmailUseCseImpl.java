package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.GetGamesByCoachUserEmailUseCse;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.GameResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGamesByCoachUserEmailUseCseImpl implements GetGamesByCoachUserEmailUseCse {
    private final GameRepository gameRepository;

    @Override
    public List<GameResponse> findGamesByCoachUserEmail(String email) {
        List<GameEntity> foundGames = gameRepository.findGamesByCoachUserEmail(email);

        return foundGames.stream().map((entity) -> GameResponse.builder()
                .season(entity.getSeason())
                .gameId(entity.getId())
                .awayTeamName(entity.getAwayTeam().getTeamName())
                .homeTeamName(entity.getHomeTeam().getTeamName())
                .build()).toList();
    }
}
