package nl.fontys.s3.indi.diamond_director_be.configuration.security.token;

public interface AccessTokenEncoder {
        String encode(AccessToken accessToken);
}
