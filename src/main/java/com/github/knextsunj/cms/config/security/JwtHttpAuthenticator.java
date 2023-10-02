package com.github.knextsunj.cms.config.security;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JwtHttpAuthenticator extends BearerHttpAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JwtHttpAuthenticator.class.getName());

    public JwtHttpAuthenticator() {
        super();
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String authorizationHeaderContent = getAuthzHeader(request);
        if (authorizationHeaderContent == null || authorizationHeaderContent.isBlank()) {
            // Create an empty authentication token since there is no
            // Authorization header.
            return createBearerToken("", request);
        }

        LOG.info("Attempting to execute login with auth header");

        final String[] principalsAndCredentials = getPrincipalsAndCredentials(authorizationHeaderContent, request);
            try {
//                String user = Jwts.parserBuilder()
//                        .setSigningKey(TokenIssuer.key)
//                        .build()
//                        .parseClaimsJws(token.replace(TokenIssuer.PREFIX, ""))
//                        .getBody()
//                        .getSubject();

                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(TokenIssuer.key)
                        .build()
                        .parseClaimsJws(principalsAndCredentials[0].replace(TokenIssuer.PREFIX, ""));
                return new ShiroJsonWebToken(jws, principalsAndCredentials[0], true);
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
                //don't trust the JWT!
                e.printStackTrace();
                LOG.error("Invalid JWT: {}",principalsAndCredentials[0],e);
                throw new AuthorizationException("Invalid JWT");
            }

    }

    @Override
    protected AuthenticationToken createBearerToken(String token, ServletRequest request) {
        return new BearerToken(token, request.getRemoteHost());
    }
}


