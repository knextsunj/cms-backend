package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.domain.AddressType
import com.github.knextsunj.cms.repository.AddressTypeRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("addressTypeStatusValidationService")
open class AddressTypeValidationServiceImpl:GenericValidationService {

    @Autowired
    open lateinit var addressTypeRepository: AddressTypeRepository

    override fun deDup(name: String?): Boolean? {
        var addressType: AddressType = addressTypeRepository.findAddressTypeByName(name)
        return CmsUtil.isNull(addressType)

    }
}