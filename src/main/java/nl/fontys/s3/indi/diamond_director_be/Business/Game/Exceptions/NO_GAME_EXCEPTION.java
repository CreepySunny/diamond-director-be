package nl.fontys.s3.indi.diamond_director_be.Business.Game.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NO_GAME_EXCEPTION extends HttpStatusCodeException {
    public NO_GAME_EXCEPTION() {super(HttpStatus.BAD_REQUEST, "NO_GAME_BY_ID");}
}
