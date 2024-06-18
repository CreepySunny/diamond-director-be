package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
