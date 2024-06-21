package nl.fontys.s3.indi.diamond_director_be.Business.Player.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions.UnauthorizedDataAccessException;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.FindPlayerByUserIdUseCase;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessToken;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPlayerByUserIdUseCaseImpl implements FindPlayerByUserIdUseCase {
    private final PlayerRepository playerRepository;
    private final AccessToken requestAccessToken;

    @Override
    @Transactional
    public Player findPlayerByUserId(Long userId) {
        if (!requestAccessToken.hasRole(UserRoles.PLAYER))
        {
            if (requestAccessToken.getUserId() != userId)
            {
                throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }

        return PlayerConverter.convert(playerRepository.findByUserEntityId(userId).orElseThrow(NO_COACH_EXCEPTION::new));
    }
}
