package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.CustomerValidationService
import com.github.knextsunj.cms.domain.Customer
import com.github.knextsunj.cms.repository.CustomerRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

//@Component("customerValidationService")
@RequestScoped
@CustomerValidationService
open class CustomerValidationServiceImpl: GenericValidationService {

    @Inject
    private lateinit var customerRepository: CustomerRepository

    override fun deDup(name: String?): Boolean? {
       var customer: Customer? = customerRepository.findByName(name);
        return CmsUtil.isNull(customer);
//        return false;
    }


}