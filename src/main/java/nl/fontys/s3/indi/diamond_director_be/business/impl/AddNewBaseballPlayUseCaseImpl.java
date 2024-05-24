package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.AddNewBaseballPlayUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddNewBaseballPlayUseCaseImpl implements AddNewBaseballPlayUseCase {
    @Override
    public void createNewPlayAndScore(ScoreRequest request) {

    }
}
