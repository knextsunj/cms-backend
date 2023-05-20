package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CountryDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("deleted") String deleted) {
}
