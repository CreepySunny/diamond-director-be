package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.business.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.ScoreRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddNewBaseballPlayUseCaseImplTest {
    @Mock
    private PlayRepository playRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private AddNewBaseballPlayUseCaseImpl baseballPlayUseCase;

}