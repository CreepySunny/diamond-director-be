package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.FindPlayerByUserIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
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
