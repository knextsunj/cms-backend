package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.domain.UserPreference;
import com.github.knextsunj.cms.dto.UserPreferenceDTO;
import com.github.knextsunj.cms.mapper.UserPreferenceMapper;
import com.github.knextsunj.cms.repository.UserPreferenceRepository;
import com.github.knextsunj.cms.repository.UserRepository;
import com.github.knextsunj.cms.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    @Override
    public UserPreferenceDTO retrieveUserPreferences(String username) {

       User user = userRepository.findByUsername(username);

        List<UserPreference> userPreferenceList = userPreferenceRepository.findByUserId(user.getId());
        return userPreferenceMapper.toUserPreferencesDTO(user.getId(), userPreferenceList);
    }
}
