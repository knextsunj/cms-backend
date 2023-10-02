package com.github.knextsunj.cms.config.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collections;

public class JwtRealm extends AuthorizingRealm {


    public JwtRealm() {

    }

    @Override
    public String getName() {
        return "jwt";
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(Collections.emptySet());
        simpleAuthorizationInfo.setStringPermissions(Collections.emptySet());
        simpleAuthorizationInfo.setObjectPermissions(Collections.emptySet());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroJsonWebToken jwt = (ShiroJsonWebToken) authenticationToken;
        return new SimpleAuthenticationInfo(jwt.getPrincipal(), jwt, getName());
    }
}
