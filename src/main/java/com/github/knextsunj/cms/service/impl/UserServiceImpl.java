package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceImpl implements UserService {

//    @Autowired
    private UserRepository userRepository;

    @Override
    public String getRoleForUser(String username) {
        return null;
    }
}
