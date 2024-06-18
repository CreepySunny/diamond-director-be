package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class InvalidAccessTokenException extends HttpStatusCodeException {
    public InvalidAccessTokenException(String errorCause) {
        super(HttpStatus.UNAUTHORIZED, errorCause);
    }
}
