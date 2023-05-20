package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.domain.City
import com.github.knextsunj.cms.domain.State
import com.github.knextsunj.cms.repository.CityRepository
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.util.CmsUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("cityValidationService")
open class CityValidationServiceImpl : GenericValidationService {

    @Autowired
    open lateinit var cityRepository: CityRepository

    override fun deDup(name: String?): Boolean? {
        var city: City = cityRepository.findCityByName(name);
        return CmsUtil.isNull(city);
    }


}