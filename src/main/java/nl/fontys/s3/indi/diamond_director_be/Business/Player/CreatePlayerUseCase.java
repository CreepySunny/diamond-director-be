package nl.fontys.s3.indi.diamond_director_be.Business.Player;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.CreatePlayerRequest;

import java.text.ParseException;

public interface CreatePlayerUseCase {
    CreateUserResponse create(CreatePlayerRequest request);
}
