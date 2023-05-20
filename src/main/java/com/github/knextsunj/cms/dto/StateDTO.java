package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StateDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                       @JsonProperty("deleted") String deleted, @JsonProperty("countryId") Long countryId,
                       @JsonProperty("countryName") String countryName) {
}
