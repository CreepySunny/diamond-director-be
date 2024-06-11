package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.GetGamesByCoachIdUseCse;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.GameResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGamesByCoachIdUseCseImpl implements GetGamesByCoachIdUseCse {
    private final GameRepository gameRepository;

    @Override
    public List<GameResponse> findGamesByCoachId(long coachId) {
        List<GameEntity> foundGames = gameRepository.findGamesByCoachId(coachId);

        return foundGames.stream().map((entity) -> GameResponse.builder()
                .season(entity.getSeason())
                .gameId(entity.getId())
                .awayTeamName(entity.getAwayTeam().getTeamName())
                .homeTeamName(entity.getHomeTeam().getTeamName())
                .build()).toList();
    }
}
