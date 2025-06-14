package nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessToken;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessTokenEncoder;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.AccessTokenDecoder;
import nl.fontys.s3.indi.diamond_director_be.Configuration.security.token.Exceptions.InvalidAccessTokenException;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();

        claimsMap.put("roles", accessToken.getUserRole());

        if (accessToken.getUserId() != null) {
            claimsMap.put("userId", accessToken.getUserId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();

            UserRoles role = UserRoles.valueOf(claims.get("roles", String.class));
            Long userId = claims.get("userId", Long.class);

            return new AccessTokenImpl(claims.getSubject(), userId, role);
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
