package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CityDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                      @JsonProperty("deleted") String deleted,
                      @JsonProperty("stateId") Long stateId, @JsonProperty("stateName") String stateName) implements Serializable {
}
