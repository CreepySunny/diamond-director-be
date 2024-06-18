package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token;

public interface AccessTokenEncoder {
        String encode(AccessToken accessToken);
}
