package com.github.knextsunj.cms.service.validation

interface GenericValidationService {

    fun deDup(name: String?): Boolean?
}