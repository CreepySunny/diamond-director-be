package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.CreatePlayerRequest;

import java.text.ParseException;

public interface CreatePlayerUseCase {
    CreateUserResponse create(CreatePlayerRequest request) throws ParseException;
}
