package com.github.knextsunj.cms.service.validation.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.repository.StateRepository
import com.github.knextsunj.cms.util.CmsUtil
import com.github.knextsunj.cms.domain.State

@Component("stateValidationService")
open class StateValidationServiceImpl:GenericValidationService {

    @Autowired
    open lateinit var stateRepository: StateRepository
	
	override fun deDup(name: String?): Boolean? {
	        var state: State? = stateRepository.findStateByName(name);
        return CmsUtil.isNull(state);	
	}	
	

}