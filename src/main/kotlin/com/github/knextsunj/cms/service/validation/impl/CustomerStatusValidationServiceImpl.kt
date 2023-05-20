package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.domain.City
import com.github.knextsunj.cms.domain.CustomerStatus
import com.github.knextsunj.cms.repository.CustomerStatusRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("customerStatusValidationService")
open class CustomerStatusValidationServiceImpl:GenericValidationService {

    @Autowired
    open lateinit var customerStatusRepository: CustomerStatusRepository

    override fun deDup(name: String?): Boolean? {
        var customerStatus: CustomerStatus = customerStatusRepository.findCustomerStatusByName(name);
        return CmsUtil.isNull(customerStatus);

    }
}