package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.domain.UserPreference;
import com.github.knextsunj.cms.dto.UserPreferenceDTO;
import com.github.knextsunj.cms.mapper.UserPreferenceMapper;
import com.github.knextsunj.cms.repository.UserPreferenceRepository;
import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserPreferenceService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local(UserPreferenceService.class)
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Inject
    private UserPreferenceRepository userPreferenceRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserPreferenceMapper userPreferenceMapper;

    @Override
    public UserPreferenceDTO retrieveUserPreferences(String username) {

       User user = userRepository.findByUsername(username);

        List<UserPreference> userPreferenceList = userPreferenceRepository.findByUserId(user.getId());
        return userPreferenceMapper.toUserPreferencesDTO(user.getId(), userPreferenceList);
    }
}
