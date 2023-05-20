package com.github.knextsunj.cms.dto;

import java.util.List;

public record UserPreferenceDTO(Long userId, List<String> pageNames) {
}
