package com.github.knextsunj.cms.config.security;

import com.github.knextsunj.cms.annotation.JWTRequired;
import io.jsonwebtoken.*;

import javax.annotation.Priority;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@JWTRequired
@Priority(Priorities.AUTHENTICATION)
public class JWTFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String header = requestContext
                .getHeaderString(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            throw new NotAuthorizedException(
                    "Authorization header must be provided");
        }
        // Get token from the HTTP Authorization header
        String token = header.substring("Bearer".length()).trim();
        System.out.println("token found [" + token + "]");
        String user = getUserIfValid(token);
        System.out.println("user found " + user);
        //set user in context if required

    }

    private String getUserIfValid(String token) {
        Key key = new SecretKeySpec("secret".getBytes(), "DES");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token);
            String scope = claims.getBody().get("scope", String.class);
            System.out.println("scope " + scope);
            return claims.getBody().getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            //don't trust the JWT!
            e.printStackTrace();
            throw new NotAuthorizedException("Invalid JWT");
        }
    }

}
