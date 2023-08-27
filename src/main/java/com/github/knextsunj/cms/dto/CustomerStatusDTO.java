package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CustomerStatusDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("deleted") String deleted) implements Serializable {
}
