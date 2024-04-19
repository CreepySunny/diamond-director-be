package nl.fontys.s3.indi.diamond_director_be.business;

import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerResponse;

import java.text.ParseException;

public interface CreatePlayerUseCase {
    CreatePlayerResponse create(CreatePlayerRequest request) throws ParseException;
}
