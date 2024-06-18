package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.AssignPlayerToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions.NO_PLAYER_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignPlayerTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssignPlayerToTeamUseCaseImpl implements AssignPlayerToTeamUseCase {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public void assignPlayerToTeam(AssignPlayerTeamRequest request) {
        TeamEntity foundTeam = teamRepository.findByTeamName(request.getTeamName()).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);
        PlayerEntity foundPlayer = playerRepository.findById(request.getPlayerId()).orElseThrow(NO_PLAYER_EXCEPTION::new);

        foundPlayer.setTeam(foundTeam);

        playerRepository.save(foundPlayer);
    }
}
