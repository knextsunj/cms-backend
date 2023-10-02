package com.github.knextsunj.cms.config.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@Dependent
@WebListener
public class EnvListener extends EnvironmentLoaderListener {

    @Inject
    private JpaRealm jpaRealm;

    @Inject
    private JwtRealm jwtRealm;

    public EnvListener() {
        super();
    }

    @Override
    protected WebEnvironment createEnvironment(ServletContext pServletContext) {
        List<Realm> realmList = new ArrayList<>();
        JwtCredentialsMatcher jwtCredentialsMatcher = new JwtCredentialsMatcher();
        WebEnvironment environment = super.createEnvironment(pServletContext);
        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
        BcryptCredentialsMatcher bcryptCredentialsMatcher = new BcryptCredentialsMatcher();
        jpaRealm.setCredentialsMatcher(bcryptCredentialsMatcher);
        jpaRealm.setAuthenticationTokenClass(UsernamePasswordToken.class);
        jwtRealm.setAuthenticationTokenClass(ShiroJsonWebToken.class);
        jwtRealm.setCredentialsMatcher(jwtCredentialsMatcher);
        realmList.add(jwtRealm);
        realmList.add(jpaRealm);
        rsm.setRealms(realmList);
        ((DefaultWebEnvironment) environment).setSecurityManager(rsm);
        return environment;
    }
}
