package nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class DUP_EMAIL_EXCEPTION extends HttpStatusCodeException {
    public DUP_EMAIL_EXCEPTION() {
        super(HttpStatus.BAD_REQUEST, "DUPLICATE_EMAIL");
    }
}
