package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.CityValidationService
import com.github.knextsunj.cms.domain.City
import com.github.knextsunj.cms.repository.CityRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
@CityValidationService
open class CityValidationServiceImpl : GenericValidationService {

    @Inject
    private lateinit var cityRepository: CityRepository

    override fun deDup(name: String?): Boolean? {
        var city: City? = cityRepository.findCityByName(name);
        return CmsUtil.isNull(city);
//        return false;
    }


}