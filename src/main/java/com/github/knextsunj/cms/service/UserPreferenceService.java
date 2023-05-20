package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.UserPreferenceDTO;

public interface UserPreferenceService {

    UserPreferenceDTO retrieveUserPreferences(String username);
}
