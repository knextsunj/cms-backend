package com.github.knextsunj.cms.service.validation.impl

import com.github.knextsunj.cms.annotation.StateValidationService
import com.github.knextsunj.cms.domain.State
import com.github.knextsunj.cms.service.validation.GenericValidationService
import com.github.knextsunj.cms.repository.StateRepository
import com.github.knextsunj.cms.util.CmsUtil

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
@StateValidationService
open class StateValidationServiceImpl:GenericValidationService {

    @Inject
    private lateinit var stateRepository: StateRepository
	
	override fun deDup(name: String?): Boolean? {
	        var state: State? = stateRepository.findStateByName(name);
        return CmsUtil.isNull(state);
//        return false;
	}	
	

}