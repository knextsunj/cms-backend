package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.repository.UserRepository;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Objects;

@Stateless

public class UserDetailsServiceImpl  {

    @Inject
    private UserRepository repository;

    public UserDetails loadUserByUsername(String username) {

//        List<User> userList =repository.findAll();
//        User user = repository.findByUsername(username);
//
//        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
//        if (Objects.nonNull(user)) {
//            builder = org.springframework.security.core.userdetails.User.withUsername(username);
//            builder.password(user.getPassword());
//            builder.roles(user.getRole());
//        } else {
//            throw new UsernameNotFoundException("User not found.");
//        }
//
//        return builder.build();

        return null;
    }

    class UserDetails {

    }
}
