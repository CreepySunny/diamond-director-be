package nl.fontys.s3.indi.diamond_director_be.business.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class DUP_EMAIL_EXCEPTION extends HttpStatusCodeException {
    public DUP_EMAIL_EXCEPTION() {
        super(HttpStatus.BAD_REQUEST, "DUPLICATE_EMAIL");
    }
}
