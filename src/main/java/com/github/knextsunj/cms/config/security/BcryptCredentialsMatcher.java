package com.github.knextsunj.cms.config.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class BcryptCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String password = new String(userToken.getPassword());
        BCrypt.Result authResult = BCrypt.verifyer().verify(password.toCharArray(), (String)  info.getCredentials());
        return authResult.verified;
    }
}
