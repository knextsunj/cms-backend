package com.github.knextsunj.cms.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CityDTO(@JsonProperty("id") val id:Long?, @JsonProperty("name") val name:String?,
                   @JsonProperty("deleted") val deleted:String?,
                   @JsonProperty("stateId") val stateId:Long?, @JsonProperty("stateName") val stateName:String?)
