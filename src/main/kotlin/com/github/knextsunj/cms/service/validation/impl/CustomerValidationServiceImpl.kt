package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.domain.Customer
import com.github.knextsunj.cms.repository.CustomerRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("customerValidationService")
open class CustomerValidationServiceImpl: GenericValidationService {

    @Autowired
    open lateinit var customerRepository: CustomerRepository

    override fun deDup(name: String?): Boolean? {
       var customer: Customer? = customerRepository.findByName(name);
        return CmsUtil.isNull(customer);
    }


}