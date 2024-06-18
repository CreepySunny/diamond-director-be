package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.FindAllPlayersNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllPlayersNoTeamUseCaseImpl implements FindAllPlayersNoTeamUseCase {
    private PlayerRepository playerRepository;
    @Override
    public List<Player> findPlayersNoTeam() {
        return playerRepository.findByTeamIsNull().stream()
                .map(PlayerConverter::convert)
                .toList();
    }
}
