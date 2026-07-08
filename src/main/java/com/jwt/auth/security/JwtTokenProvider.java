package com.jwt.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private static final String ROLE_CLAIM = "role";

    private final JwtProperties jwtProperties;
    private final Algorithm algorithm;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
    }

    public String generateToken(String subject, String role) {

        Instant now = Instant.now();
        Instant expiresAt = now.plusMillis(jwtProperties.getExpiration());

        return JWT.create()
                .withIssuer(jwtProperties.getIssuer())
                .withSubject(subject)
                .withClaim(ROLE_CLAIM, role)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiresAt))
                .sign(algorithm);
    }

    public boolean isTokenValid(String token) {
        try {
            verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public String getSubject(String token) {
        return verify(token)
                .getSubject();
    }

    public String getRole(String token) {
        return verify(token)
                .getClaim(ROLE_CLAIM)
                .asString();
    }

    public Instant getExpiration(String token) {
        return verify(token)
                .getExpiresAt()
                .toInstant();
    }

    public DecodedJWT verify(String token) {
        return JWT.require(algorithm)
                .withIssuer(jwtProperties.getIssuer())
                .build()
                .verify(token);
    }

    public long getExpirationInSeconds() {
        return jwtProperties.getExpiration() / 1000;
    }
}


