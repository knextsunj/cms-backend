package com.github.knextsunj.cms.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;


public class TokenIssuer {

//    public static final long EXPIRY_MINS = 60L;

    public static final long EXPIRATIONTIME =  900000;

   public  static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static final String PREFIX = "Bearer";


    //Issue token for given username
    public String issueToken(String username) {
        //Exiration requires a Date, so use below to get the Date instance
//        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(EXPIRY_MINS);
//        Date expirationDateTime = Date.from(
//                expiryPeriod.atZone(ZoneId.systemDefault())
//                        .toInstant());
        //Use "secret" to generate a JWT
//        Key key = new SecretKeySpec("secret".getBytes(), SignatureAlgorithm.HS256.getValue());
//        String compactJws = Jwts.builder()
//                .setSubject(username) // The subject will be the username itself
//                .claim("scope", "admin") //Can be used to set roles
//                .signWith(SignatureAlgorithm.HS256, key) // The alg used
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDateTime) //Expire token after this
//                .compact();

        String compactJws = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();

        return compactJws;
    }
}
