package com.github.knextsunj.cms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class StateDTO(@JsonProperty("id") val  id:Long?, @JsonProperty("name") val name:String?,
                    @JsonProperty("deleted") val deleted:String?, @JsonProperty("countryId") val countryId:Long?,
                    @JsonProperty("countryName") val countryName:String?): Serializable
