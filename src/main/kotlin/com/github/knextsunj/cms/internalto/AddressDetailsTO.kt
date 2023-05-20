package com.github.knextsunj.cms.internalto

import com.github.knextsunj.cms.domain.*
import com.github.knextsunj.cms.dto.AddressDTO

data class AddressDetailsTO(val customer: Customer, val city: City, val state: State, val country: Country,
                            val addressType: AddressType, val address:Address,val toUpdate:Boolean,val addressDTO: AddressDTO)
