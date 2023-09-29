package com.github.knextsunj.cms.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.knextsunj.cms.domain.AccountCredentials;
import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.dto.AuthenticatedUser;
import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserService;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Objects;

@Stateless
@Local(UserService.class)
public class UserServiceImpl {

    @Inject
    private UserRepository userRepository;

    public AuthenticatedUser validateUser(AccountCredentials accountCredentials) {

        User user = userRepository.findByUsername(accountCredentials.getUsername());
        if(Objects.nonNull(user)) {
            BCrypt.Result authResult = BCrypt.verifyer().verify(accountCredentials.getPassword().toCharArray(), user.getPassword());
            if(authResult.verified) {
                AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.getUsername(),user.getRole());
                return  authenticatedUser;
            }
        }
        return null;
    }
}
