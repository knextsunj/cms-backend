package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.CustomerStatusValidationService
import com.github.knextsunj.cms.domain.CustomerStatus
import com.github.knextsunj.cms.repository.CustomerStatusRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
@CustomerStatusValidationService
open class CustomerStatusValidationServiceImpl:GenericValidationService {

    @Inject
    private lateinit var customerStatusRepository: CustomerStatusRepository

    override fun deDup(name: String?): Boolean? {
        var customerStatus: CustomerStatus? = customerStatusRepository.findCustomerStatusByName(name);
        return CmsUtil.isNull(customerStatus);
//        return false;
    }
}