package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

public record CustomerDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                          @JsonProperty("deleted") String deleted,
                          @JsonSerialize(using = LocalDateSerializer.class)  @JsonFormat(pattern = "dd/MM/yyyy") @JsonDeserialize(using = LocalDateDeserializer.class)
                          @JsonProperty("dob") LocalDate dob,
                          @JsonProperty("gender") String gender, @JsonProperty("mobileNo") Long mobileNo,
                          @JsonProperty("emailAddress") String emailAddress,
                          @JsonProperty("customerStatusDescr") String customerStatusDescr) implements Serializable {
}
