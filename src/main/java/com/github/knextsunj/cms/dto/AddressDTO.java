package com.github.knextsunj.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                         @JsonProperty("deleted") String deleted,
                         @JsonProperty("street") String street, @JsonProperty("locality") String locality,
                         @JsonProperty("area") String area,
                         @JsonProperty("pincode") Long pincode, @JsonProperty("cityId") Long cityId,
                         @JsonProperty("stateId") Long stateId,
                         @JsonProperty("countryId") Long countryId, @JsonProperty("addressTypeId") Long addressTypeId,
                         @JsonProperty("customerId") Long customerId) {
}
