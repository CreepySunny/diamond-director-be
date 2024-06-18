package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions.NO_TEAM_FOUND_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.FindAllPlayerFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllPlayerFromTeamNameUseCaseImpl implements FindAllPlayerFromTeamNameUseCase {
    private final TeamRepository teamRepository;

    @Override
    public List<Player> findPlayersFromTeamName(String teamName) {
        TeamEntity foundTeam = teamRepository.findByTeamName(teamName).orElseThrow(NO_TEAM_FOUND_EXCEPTION::new);

        return foundTeam.getPlayers()
                .stream()
                .map(PlayerConverter::convert)
                .toList();
    }
}
