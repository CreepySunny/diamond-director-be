package nl.fontys.s3.indi.diamond_director_be.Business.Game;

import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.ScoreRequest;

public interface AddNewBaseballPlayUseCase {
    Game createNewPlayAndScore(ScoreRequest request);
}
