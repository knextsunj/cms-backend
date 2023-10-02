package com.github.knextsunj.cms.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class JwtCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        ShiroJsonWebToken credentials = (ShiroJsonWebToken) info.getCredentials();
        Jws<Claims> jws = credentials.getCredentials();
        Object principal = token.getPrincipal();

        if (!principal.equals(credentials.getPrincipal())) {
            return false;
        }
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(TokenIssuer.key)
                .build();

        if (!credentials.isValidated() && !jwtParser.isSigned(credentials.getRawToken())) {
            return false;
        }

        final Jws<Claims> reParsedJws = jwtParser.parseClaimsJws(credentials.getRawToken());

        return reParsedJws.getHeader().equals(jws.getHeader())
                && reParsedJws.getBody().equals(jws.getBody())
                && reParsedJws.getSignature().equals(jws.getSignature());
    }
}
