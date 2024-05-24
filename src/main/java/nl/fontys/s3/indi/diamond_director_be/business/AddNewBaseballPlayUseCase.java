package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;

public interface AddNewBaseballPlayUseCase {
    void createNewPlayAndScore(ScoreRequest request);
}
