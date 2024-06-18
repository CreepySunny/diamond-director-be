package nl.fontys.s3.indi.diamond_director_be.Business.Player.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NO_PLAYER_EXCEPTION extends HttpStatusCodeException {
    public NO_PLAYER_EXCEPTION() {super(HttpStatus.BAD_REQUEST, "NO_PLAYER_BY_ID");}
}
