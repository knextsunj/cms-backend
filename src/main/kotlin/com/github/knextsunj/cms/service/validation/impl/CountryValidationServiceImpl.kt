package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.CountryValidationService
import com.github.knextsunj.cms.domain.Country
import com.github.knextsunj.cms.repository.CountryRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

//@Component("countryValidationService")
@RequestScoped
@CountryValidationService
open class CountryValidationServiceImpl:GenericValidationService {

    @Inject
    private lateinit var countryRepository: CountryRepository


    override fun deDup(name: String?): Boolean? {
        var country: Country? = countryRepository.findByName(name);
        return CmsUtil.isNull(country);
//        return false;
    }
}