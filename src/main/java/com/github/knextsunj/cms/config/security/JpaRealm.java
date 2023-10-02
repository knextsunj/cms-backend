package com.github.knextsunj.cms.config.security;

import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.inject.Inject;

public class JpaRealm extends AuthorizingRealm {

    private static final ThreadLocal<UsernamePasswordToken> userCredentialThreadToken = new ThreadLocal<>();

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    private static String REALM_NAME = "jpaRealm";

    public JpaRealm() {
        setName(REALM_NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userThreadLocal.get();
        String role = user.getRole();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (role != null) {
            simpleAuthorizationInfo.addRole(role);
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        userCredentialThreadToken.set(token);
        User user = userRepository.findByUsername(token.getUsername());
        userThreadLocal.set(user);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), REALM_NAME);
        if (simpleAuthenticationInfo != null) {
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
