package com.github.knextsunj.cms.util

import com.github.knextsunj.cms.dto.AddressDTO
import com.github.knextsunj.cms.exception.ValidationFailureException

open class ValidationUtil {

    fun checkAddressParams(addressDTO: AddressDTO):Boolean? {
        val result = addressDTO?:-1
        if(result==-1) {
            throw ValidationFailureException("AddressDTO mandatory params check:Input addressDTO is null")
        }
        else {
            if(null!=addressDTO.addressTypeId && null!=addressDTO.cityId && null!=addressDTO.stateId && null!=addressDTO.countryId && null!=addressDTO.addressTypeId
                && null!=addressDTO.customerId && null!=addressDTO.area && null!=addressDTO.pincode) {
                return true
            }
        }

        return false
    }

}