package nl.fontys.s3.indi.diamond_director_be.Business.Coach.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NO_COACH_EXCEPTION extends HttpStatusCodeException {
    public NO_COACH_EXCEPTION() {super(HttpStatus.BAD_REQUEST, "NO_COACH_FOUND");}
}
