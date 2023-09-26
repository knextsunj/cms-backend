package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.UserPreference;
import com.github.knextsunj.cms.dto.UserPreferenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface UserPreferenceMapper {

    default UserPreferenceDTO toUserPreferencesDTO(Long userId, List<UserPreference> userPreferences) {
        return new UserPreferenceDTO(userId, userPreferences.parallelStream().map(userPreference -> {
            return userPreference.getPageName();
        }).collect(Collectors.toList()));
    }


}
