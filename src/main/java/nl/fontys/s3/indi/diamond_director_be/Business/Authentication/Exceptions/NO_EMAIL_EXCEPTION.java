package nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NO_EMAIL_EXCEPTION extends HttpStatusCodeException {
    public NO_EMAIL_EXCEPTION() {
        super(HttpStatus.NO_CONTENT, "NO_USER_WITH_EMAIL");
    }

}
