package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.AddNewBaseballPlayUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddNewBaseballPlayUseCaseImpl implements AddNewBaseballPlayUseCase {
    private final GameRepository gameRepository;

    @Override
    public void createNewPlayAndScore(ScoreRequest request) {

    }

    private Game findGameFromId(Long gameId){
        Optional<GameEntity> returnedGameEntity = gameRepository.findById(gameId);


        return null;
    }
}
