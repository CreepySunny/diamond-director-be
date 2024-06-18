package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.GameResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGamesByCoachUserEmailUseCseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GetGamesByCoachUserEmailUseCseImpl getGamesByCoachIdUseCse;

    private GameEntity gameEntity;
    private List<GameEntity> gameEntities;

    @BeforeEach
    void setUp() {
        gameEntity = new GameEntity();
        gameEntity.setId(1L);
        gameEntity.setSeason("2023");

        TeamEntity awayTeam = new TeamEntity();
        awayTeam.setTeamName("Away Team");

        TeamEntity homeTeam = new TeamEntity();
        homeTeam.setTeamName("Home Team");

        gameEntity.setAwayTeam(awayTeam);
        gameEntity.setHomeTeam(homeTeam);

        gameEntities = new ArrayList<>();
        gameEntities.add(gameEntity);
    }

    @Test
    void findGamesByCoachId_gamesFound() {
        when(gameRepository.findGamesByCoachUserEmail(anyString())).thenReturn(gameEntities);

        List<GameResponse> responses = getGamesByCoachIdUseCse.findGamesByCoachUserEmail("jesus@example.com");

        assertNotNull(responses);
        assertEquals(1, responses.size());

        GameResponse response = responses.get(0);
        assertEquals("2023", response.getSeason());
        assertEquals(1L, response.getGameId());
        assertEquals("Away Team", response.getAwayTeamName());
        assertEquals("Home Team", response.getHomeTeamName());
    }

    @Test
    void findGamesByCoachId_noGamesFound() {
        when(gameRepository.findGamesByCoachUserEmail(anyString())).thenReturn(new ArrayList<>());

        List<GameResponse> responses = getGamesByCoachIdUseCse.findGamesByCoachUserEmail("comcast.example.com");

        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }
}