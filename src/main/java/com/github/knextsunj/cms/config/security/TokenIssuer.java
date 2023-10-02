package com.github.knextsunj.cms.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


public class TokenIssuer {

    public static final long EXPIRATIONTIME =  900000;

   public  static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static final String PREFIX = "Bearer";


    //Issue token for given username
    public String issueToken(String username) {
        String compactJws = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();

        return compactJws;
    }
}
