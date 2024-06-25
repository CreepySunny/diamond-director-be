package nl.fontys.s3.indi.diamond_director_be.Configuration.db;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.CreateGameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.CreateTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CoachPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CreateCoachRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.CreateGameRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerHanded;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.GameRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DummyDbInitializer {
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final CreateCoachUseCase createCoachUseCase;
    private final CreateTeamUseCase createTeamUseCase;
    private final CreatePlayerUseCase createPlayerUseCase;
    private final CreateGameUseCase createGameUseCase;


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initialize() {
        if (isCoachRepoEmpty()){
            InsertMockCoaches();
        }

        if (isTeamRepoEmpty()){
            InsertMockTeams();
        }

        if (isPlayerRepoEmpty()){
            InsertMockPlayers();
        }

        if (isGameRepoEmpty()){
            InsertMockGames();
        }
    }


    private boolean isTeamRepoEmpty() {
        return teamRepository.count() == 0;
    }

    private boolean isCoachRepoEmpty() {
        return coachRepository.count() == 0;
    }

    private boolean isPlayerRepoEmpty() {
        return playerRepository.count() == 0;
    }

    private boolean isGameRepoEmpty() {
        return gameRepository.count() == 0;
    }

    private void InsertMockCoaches(){
        CreateCoachRequest createCoachRequest = CreateCoachRequest.builder()
                .firstName("Coach1")
                .lastName("Prime")
                .email("coach1@gmail.com")
                .dateOfBirth(LocalDate.now())
                .position(CoachPosition.HEAD)
                .canScoreKeep(false)
                .password("123")
                .build();
        CreateCoachRequest createCoachRequest2 = CreateCoachRequest.builder()
                .firstName("Coach2")
                .lastName("Prime")
                .email("coach2@gmail.com")
                .dateOfBirth(LocalDate.now())
                .position(CoachPosition.HEAD)
                .canScoreKeep(false)
                .password("123")
                .build();

        createCoachUseCase.createCoach(createCoachRequest);
        createCoachUseCase.createCoach(createCoachRequest2);
    }

    private void InsertMockTeams(){
        CreateTeamRequest teamRequest = CreateTeamRequest.builder()
                .teamName("Team 1")
                .createCoachUserEmail("coach1@gmail.com")
                .build();
        CreateTeamRequest teamRequest2 = CreateTeamRequest.builder()
                .teamName("Team 2")
                .createCoachUserEmail("coach2@gmail.com")
                .build();

        createTeamUseCase.createTeam(teamRequest);
        createTeamUseCase.createTeam(teamRequest2);
    }

    private void InsertMockPlayers(){
        CreatePlayerRequest playerRequest1 = CreatePlayerRequest.builder()
                .firstName("Player1")
                .lastName("Prime")
                .email("player1@gmail.com")
                .password("123")
                .dateOfBirth(LocalDate.now())
                .position(PlayerPosition.center_field)
                .handed_bats(PlayerHanded.RIGHT)
                .handed_throws(PlayerHanded.RIGHT)
                .height(1.87D)
                .weight(87.0D)
                .build();


        CreatePlayerRequest playerRequest2 = CreatePlayerRequest.builder()
                .firstName("Player2")
                .lastName("Prime")
                .email("player2@gmail.com")
                .password("123")
                .dateOfBirth(LocalDate.now())
                .position(PlayerPosition.center_field)
                .handed_bats(PlayerHanded.RIGHT)
                .handed_throws(PlayerHanded.RIGHT)
                .height(1.87D)
                .weight(87.0D)
                .build();

        createPlayerUseCase.create(playerRequest1);
        createPlayerUseCase.create(playerRequest2);
    }

    private void InsertMockGames() {
        CreateGameRequest createGameRequest = CreateGameRequest.builder()
                .season("2024")
                .awayTeamName("Team 1")
                .homeTeamName("Team 2")
                .build();

        createGameUseCase.createGame(createGameRequest);
    }
}