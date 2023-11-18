package com.github.knextsunj.cms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class AddressDTO(@JsonProperty("id") val id:Long?, @JsonProperty("name") val name:String?,
                      @JsonProperty("deleted")  val deleted:String?,
                      @JsonProperty("street")  val street:String?, @JsonProperty("locality") val locality:String?,
                      @JsonProperty("area")  val area:String?,
                      @JsonProperty("pincode")  val pincode:Long?, @JsonProperty("cityId") val cityId:Long?,
                      @JsonProperty("stateId") val stateId:Long?,
                      @JsonProperty("countryId")  val countryId:Long?, @JsonProperty("addressTypeId") val addressTypeId:Long?,
                      @JsonProperty("customerId") val customerId:Long?): Serializable
