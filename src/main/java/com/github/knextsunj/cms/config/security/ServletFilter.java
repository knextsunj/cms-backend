package com.github.knextsunj.cms.config.security;

import org.apache.shiro.web.servlet.ShiroFilter;

import javax.enterprise.context.Dependent;
import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@Dependent
@WebFilter(
        asyncSupported = true,
        urlPatterns = {"/*"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR, DispatcherType.ASYNC}
)
public class ServletFilter extends ShiroFilter {

}
