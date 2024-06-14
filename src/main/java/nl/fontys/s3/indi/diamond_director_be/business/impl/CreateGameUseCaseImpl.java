package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateGameUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.CreateGameRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateGameUseCaseImpl implements CreateGameUseCase {
    private final TeamRepository teamRepository;
    private GameRepository gameRepository;

    @Override
    public Long createGame(CreateGameRequest request) {
        GameEntity newGame = GameEntity.builder()
                .season(request.getSeason())
                .homeTeam(findTeamByName(request.getHomeTeamName()))
                .awayTeam(findTeamByName(request.getAwayTeamName()))
                .awayScore(0)
                .homeScore(0)
                .outs(0)
                .inning(1)
                .build();

        return gameRepository.save(newGame).getId();
    }

    private TeamEntity findTeamByName(String name){
        return teamRepository.findByTeamName(name).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
    }
}
