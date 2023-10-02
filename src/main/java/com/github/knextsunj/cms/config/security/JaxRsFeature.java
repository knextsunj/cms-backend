package com.github.knextsunj.cms.config.security;

import org.apache.shiro.web.jaxrs.ShiroFeature;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Dependent
@Provider
public class JaxRsFeature extends ShiroFeature {

    @Override
    public boolean configure(FeatureContext context) {
        return super.configure(context);
    }
}
