package com.github.knextsunj.cms.dto

import java.io.Serializable

data class UserPreferenceDTO( val userId:Long?, val pageNames:List<String>?): Serializable
