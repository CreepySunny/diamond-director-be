package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.CreateGameRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
    public class CreateGameUseCaseImplTest {

        @Mock
        private TeamRepository teamRepository;

        @Mock
        private GameRepository gameRepository;

        @InjectMocks
        private CreateGameUseCaseImpl createGameUseCase;

    @Test
    public void testCreateGame_Success() {
        // Mock dependencies
        TeamEntity homeTeamEntity = TeamEntity.builder()
                .teamName("HomeTeam")
                .coaches(new ArrayList<>())
                .players(new ArrayList<>())
                .build();

        TeamEntity awayTeamEntity = TeamEntity.builder()
                .teamName("AwayTeam")
                .coaches(new ArrayList<>())
                .players(new ArrayList<>())
                .build();

        when(teamRepository.findByTeamName("HomeTeam")).thenReturn(Optional.of(homeTeamEntity));
        when(teamRepository.findByTeamName("AwayTeam")).thenReturn(Optional.of(awayTeamEntity));
        when(gameRepository.save(any())).thenReturn(GameEntity.builder().id(1L).build());


        // Create test data
        CreateGameRequest request = new CreateGameRequest("2024", "HomeTeam", "AwayTeam");

        // Invoke method
        Long gameId = createGameUseCase.createGame(request);

        // Verify the behavior
        assertNotNull(gameId);
        assertEquals(1L, gameId.longValue());
    }

    @Test
    public void testCreateGame_NoTeamFound() {
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());

        CreateGameRequest request = new CreateGameRequest("2024", "NonExistentTeam", "AwayTeam");

        assertThrows(NO_TEAM_FOUND_EXCEPTION.class, () -> createGameUseCase.createGame(request));
    }

}