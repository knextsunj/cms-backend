package com.github.knextsunj.cms.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.authc.HostAuthenticationToken;

public class ShiroJsonWebToken implements HostAuthenticationToken {

    private final String host;
    private final String subject;
    private final Jws<Claims> jsonWebToken;

    private final String rawToken;

    private final boolean validated;

    public ShiroJsonWebToken(Jws<Claims> jsonWebToken, String rawToken, boolean isValidated) {
        this.host = jsonWebToken.getBody().getIssuer();
        this.subject = jsonWebToken.getBody().getSubject();
        this.jsonWebToken = jsonWebToken;
        this.rawToken = rawToken;
        this.validated = isValidated;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public Object getPrincipal() {
        return this.subject;
    }

    @Override
    public Jws<Claims> getCredentials() {
        return this.jsonWebToken;
    }

    public String getRawToken() {
        return rawToken;
    }

    /**
     * Returns {@code true} if this token was validated upon creation.
     *
     * @return {@code true} if this token was validated on creation, {@code false} otherwise.
     */
    public boolean isValidated() {
        return validated;
    }
}
