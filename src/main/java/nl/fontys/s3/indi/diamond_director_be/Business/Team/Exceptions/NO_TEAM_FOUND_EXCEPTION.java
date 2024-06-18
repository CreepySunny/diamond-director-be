package nl.fontys.s3.indi.diamond_director_be.Business.Team.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NO_TEAM_FOUND_EXCEPTION extends HttpStatusCodeException {
    public NO_TEAM_FOUND_EXCEPTION() {super(HttpStatus.NOT_FOUND, "NO_TEAM_FOUND");}
}
