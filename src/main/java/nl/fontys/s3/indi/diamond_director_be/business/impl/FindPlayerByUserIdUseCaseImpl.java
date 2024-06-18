package nl.fontys.s3.indi.diamond_director_be.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.business.FindPlayerByUserIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPlayerByUserIdUseCaseImpl implements FindPlayerByUserIdUseCase {
    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public Player findPlayerByUserId(Long userId) {
        return PlayerConverter.convert(playerRepository.findByUserEntityId(userId).orElseThrow(NO_COACH_EXCEPTION::new));
    }
}
