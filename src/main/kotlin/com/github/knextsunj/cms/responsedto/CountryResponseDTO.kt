package com.github.knextsunj.cms.responsedto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JvmRecord
data class CountryResponseDTO(
    @JsonProperty("serialNo") val serialNo: Long,
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("deleted") val deleted: String
) : Serializable