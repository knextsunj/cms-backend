package com.github.knextsunj.cms.config;

import com.github.knextsunj.cms.config.security.CorsFilter;
import com.github.knextsunj.cms.config.security.JWTFilter;
import com.github.knextsunj.cms.controller.*;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import static com.github.knextsunj.cms.config.security.Constants.ADMIN;
import static com.github.knextsunj.cms.config.security.Constants.USER;

@ApplicationPath("/")
//@DeclareRoles({ADMIN, USER})
public class CmsJaxrsApplication extends Application {

//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(AddressController.class);
//        classes.add(AddressTypeController.class);
//        classes.add(CityController.class);
//        classes.add(CustomerController.class);
//        classes.add(CustomerStatusController.class);
//        classes.add(LoginController.class);
//        classes.add(StateController.class);
//        classes.add(UserPreferencesController.class);
//        classes.add(HelloController.class);
//        classes.add(CountryController.class);
//        classes.add(JacksonJaxbJsonProvider.class);
//        classes.add(CorsFilter.class);
//        classes.add(JWTFilter.class);
//        return classes;
//    }

}
