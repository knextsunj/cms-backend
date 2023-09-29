package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserRoleService;


public class UserRoleServiceImpl implements UserRoleService {

//    @Autowired
    private UserRepository userRepository;

    @Override
    public String getRoleForUser(String username) {
        return null;
    }
}
