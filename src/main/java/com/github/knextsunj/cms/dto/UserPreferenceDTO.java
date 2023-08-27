package com.github.knextsunj.cms.dto;

import java.io.Serializable;
import java.util.List;

public record UserPreferenceDTO(Long userId, List<String> pageNames) implements Serializable {
}
