package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.domain.AccountCredentials;
import com.github.knextsunj.cms.dto.AuthenticatedUser;

public interface UserService {
    AuthenticatedUser validateUser(AccountCredentials accountCredentials);
}


