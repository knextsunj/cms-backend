//package com.github.knextsunj.cms.config.security;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//import com.github.knextsunj.cms.config.security.model.JWTCredential;
//import io.jsonwebtoken.*;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import static com.github.knextsunj.cms.config.security.Constants.REMEMBERME_VALIDITY_SECONDS;
//import static java.util.stream.Collectors.joining;
//
//@RequestScoped
//public class TokenProvider {
//
//    private static final Logger LOGGER = LogManager.getLogger(TokenProvider.class.getName());
//
//
//    private static final String AUTHORITIES_KEY = "auth";
//
//    static final long EXPIRATIONTIME =  900000;
//
//    private String secretKey;
//
//    private long tokenValidity;
//
//    private long tokenValidityForRememberMe;
//
//
//
//    @PostConstruct
//    public void init() {
//        // load from config
////        this.secretKey = key.toString();
//        this.tokenValidity = TimeUnit.HOURS.toMillis(10);   //10 hours
//        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBERME_VALIDITY_SECONDS);   //24 hours
//    }
//
//    public String createToken(String username, Set<String> authorities, Boolean rememberMe,Key key) {
//        long now = (new Date()).getTime();
//        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;
//
////        return Jwts.builder()
////                .setSubject(username)
////                .claim(AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
////                .signWith(SignatureAlgorithm.HS512, secretKey)
////                .setExpiration(new Date(now + validity))
////                .compact();
//        var dateTime = System.currentTimeMillis() + EXPIRATIONTIME;
//     return   Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(dateTime))
//                .signWith(key)
//                .compact();
//
////        return null;
//    }
//
//    public JWTCredential getCredential(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//
//        Set<String> authorities
//                = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
//                .stream()
//                .collect(Collectors.toSet());
//
//        return new JWTCredential(claims.getSubject(), authorities);
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            LOGGER.log(Level.INFO, "Invalid JWT signature: {0}", e.getMessage());
//            return false;
//        }
//    }
//}
