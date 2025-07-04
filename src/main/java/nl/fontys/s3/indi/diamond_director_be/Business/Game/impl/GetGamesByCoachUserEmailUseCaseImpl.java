package nl.fontys.s3.indi.diamond_director_be.Business.Game.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.GetGamesByCoachUserEmailUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.GameResponse;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGamesByCoachUserEmailUseCaseImpl implements GetGamesByCoachUserEmailUseCase {
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
