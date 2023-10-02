package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.dto.AuthenticatedUser;
import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    public AuthenticatedUser findUser(String username) {
        User user =  userRepository.findByUsername(username);
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.getUsername(),user.getPassword());
        return authenticatedUser;
    }
}
