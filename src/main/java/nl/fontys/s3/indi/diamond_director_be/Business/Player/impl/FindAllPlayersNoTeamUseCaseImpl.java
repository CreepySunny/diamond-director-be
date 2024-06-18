package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.FindAllPlayersNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
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
