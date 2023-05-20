package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CustomerDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                          @JsonProperty("deleted") String deleted,
                          @JsonFormat(pattern = "yyyy-MM-dd") @JsonProperty("dob") LocalDateTime dob,
                          @JsonProperty("gender") String gender, @JsonProperty("mobileNo") Long mobileNo,
                          @JsonProperty("emailAddress") String emailAddress,
                          @JsonProperty("customerStatusDescr") String customerStatusDescr) {
}
