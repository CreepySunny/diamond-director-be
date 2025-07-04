package nl.fontys.s3.indi.diamond_director_be.Business.Game.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.AddNewBaseballPlayUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Converters.GameConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions.NO_GAME_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums.PlayResult;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Game;
import nl.fontys.s3.indi.diamond_director_be.Domain.GameState.ScoreRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayFielderEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AddNewBaseballPlayUseCaseImpl implements AddNewBaseballPlayUseCase {
    private final PlayRepository playRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayFielderRepository playFielderRepository;

    @Override
    @Transactional
    public Game createNewPlayAndScore(ScoreRequest request) {
        PlayResult playResult = PlayResult.fromShorthand(request.getPlayShorthand());
        Game game = findGameFromId(request.getGameId());

        Player batter = findPlayerById(request.getBatterId());

        updateGameState(game, playResult, batter);

        PlayEntity playEntity = PlayEntity.builder()
                .playResult(playResult)
                .game(GameConverter.convert(game))
                .batter(playerRepository.findById(batter.getId()).orElseThrow(NO_PLAYER_EXCEPTION::new))
                .pitcher(playerRepository.findById(request.getPitcherId()).orElseThrow(NO_PLAYER_EXCEPTION::new))
                .inning(request.getInning())
                .half(request.getHalf())
                .build();
        PlayEntity savedPlay = playRepository.save(playEntity);

        List<PlayerPosition> fielderPositions = request.getFieldersPositions();
        if (fielderPositions != null && !fielderPositions.isEmpty()) {
            List<PlayFielderEntity> playFielders = new ArrayList<>();
            for (PlayerPosition fieldPosition : fielderPositions) {
                PlayFielderEntity playFielderEntity = new PlayFielderEntity();
                playFielderEntity.setPlay(savedPlay);
                playFielderEntity.setFielder(fieldPosition);
                playFielders.add(playFielderEntity);
                playFielderRepository.save(playFielderEntity);
            }
            savedPlay.setFielders(playFielders);
        }

        GameEntity updatedGameEntity = GameConverter.convert(game);
        updatedGameEntity.setAwayTeam(teamRepository.findAwayTeamByGameId(updatedGameEntity.getId()).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new));
        updatedGameEntity.setHomeTeam(teamRepository.findHomeTeamByGameId(updatedGameEntity.getId()).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new));
        gameRepository.save(updatedGameEntity);

        return game;
    }

    private Game findGameFromId(Long gameId) {
        Optional<GameEntity> returnedGameEntity = gameRepository.findById(gameId);

        GameEntity foundGame = returnedGameEntity.orElseThrow(NO_GAME_EXCEPTION::new);

        return GameConverter.convert(foundGame);
    }

    private Player findPlayerById(Long playerId) {
        return PlayerConverter.convert(playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found")));
    }

    private void updateGameState(Game game, PlayResult playResult, Player batter) {
        switch (playResult) {
            case SINGLE:
                advanceRunners(game, 1, batter);
                break;
            case DOUBLE:
                advanceRunners(game, 2, batter);
                break;
            case TRIPLE:
                advanceRunners(game, 3, batter);
                break;
            case HOME_RUN:
                advanceRunners(game, 4, batter);
                break;
            case STRIKEOUT:
            case GROUNDOUT:
            case FLYOUT:
            case LINEOUT:
            case POP_OUT:
                game.setOuts(game.getOuts() + 1);
                break;
            case WALK:
            case NON_INTENTIONAL_WALK:
            case INTENTIONAL_WALK:
            case HIT_BY_PITCH:
                if (!game.getBaseRunners().containsKey(Bases.FIRST)) {
                    game.getBaseRunners().put(Bases.FIRST, batter);
                } else {
                    advanceRunners(game, 1, batter);
                }
                break;
            case SACRIFICE_FLY:
                game.setOuts(game.getOuts() + 1);
                advanceRunners(game, 1, batter, true);
                break;
            case ERROR:
            case FIELDERS_CHOICE:
                break;
            default:
                throw new IllegalArgumentException("Unsupported play result: " + playResult);
        }

        if (game.getOuts() >= 3) {
            changeInning(game);
        }
    }

    private void advanceRunners(Game game, int bases, Player batter) {
        advanceRunners(game, bases, batter, false);
    }

    private void advanceRunners(Game game, int bases, Player batter, boolean isSacrificeFly) {
        Map<Bases, Player> updatedBaseRunners = new HashMap<>();
        int runsScored = 0;

        for (Map.Entry<Bases, Player> entry : game.getBaseRunners().entrySet()) {
            int newBaseIndex = entry.getKey().ordinal() + bases;
            if (newBaseIndex >= Bases.values().length) {
                runsScored++;
            } else {
                Bases newBase = Bases.values()[newBaseIndex];
                updatedBaseRunners.put(newBase, entry.getValue());
            }
        }

        if (bases < Bases.values().length) {
            updatedBaseRunners.put(Bases.values()[bases - 1], batter);
        } else {
            runsScored++;
        }

        game.setBaseRunners(updatedBaseRunners);

        if (game.getCurrentHalf() == InningHalves.TOP) {
            game.setAwayScore(game.getAwayScore() + runsScored);
        } else {
            game.setHomeScore(game.getHomeScore() + runsScored);
        }

        if (isSacrificeFly) {
            game.getBaseRunners().remove(Bases.values()[bases - 1]);
        }
    }

    private void changeInning(Game game) {
        game.setOuts(0);
        game.setBaseRunners(new HashMap<>());

        if (game.getCurrentHalf() == InningHalves.TOP) {
            game.setCurrentHalf(InningHalves.BOTTOM);
        } else {
            game.setCurrentHalf(InningHalves.TOP);
            game.setInning(game.getInning() + 1);
        }
    }

}