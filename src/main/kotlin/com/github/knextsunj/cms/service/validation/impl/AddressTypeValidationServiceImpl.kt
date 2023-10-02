package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.AddressTypeValidationService
import com.github.knextsunj.cms.domain.AddressType
import com.github.knextsunj.cms.repository.AddressTypeRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil
import javax.enterprise.context.RequestScoped
import javax.inject.Inject


@RequestScoped
@AddressTypeValidationService
open class AddressTypeValidationServiceImpl:GenericValidationService {

    @Inject
    private lateinit var addressTypeRepository: AddressTypeRepository

    override fun deDup(name: String?): Boolean? {
        var addressType: AddressType? = addressTypeRepository.findAddressTypeByName(name)
        return CmsUtil.isNull(addressType)
//        return false;
    }
}