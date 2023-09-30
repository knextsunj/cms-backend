package com.github.knextsunj.cms.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AddressTypeDTO(@JsonProperty("id") val id:Long?, @JsonProperty("name") val name:String?, @JsonProperty("deleted") val deleted:String?)
