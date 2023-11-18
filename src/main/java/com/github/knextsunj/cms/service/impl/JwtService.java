package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.exception.BusinessException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

//    @Autowired
//    private Environment environment;

    // 1 day in ms
//    static final long EXPIRATIONTIME = 86400000;

    private static final Logger logger  = LoggerFactory.getLogger(JwtService.class);

    static final long EXPIRATIONTIME =  900000;
    static final String PREFIX = "Bearer";
    // Generate secret key. Only for the demonstration
    // You should read it from the application configuration
//    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private Key key;

    // Generate JWT token
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();
        return token;
    }

    // Get a token from request Authorization header,
    // parse a token and get username
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null)
                return user;
        }

        return null;
    }

    @PostConstruct
    public void init() throws IOException {
        byte[] bytes = null;
        FileInputStream fis = null;
        try {
            File file = new File("/home/kk/cm1.key");
            bytes = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(bytes);
        }
        catch (IOException ioException) {
            logger.error("Key file not found::: ",ioException);
        }
        finally {
            if(fis!=null) {
                fis.close();
            }
        }

        if(null!=bytes) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
            key = secretKeySpec;
        }
        else {
            throw new BusinessException("Unable to construct jwt key");
        }

    }
}
