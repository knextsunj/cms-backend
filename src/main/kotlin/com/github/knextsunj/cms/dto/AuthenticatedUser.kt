package com.github.knextsunj.cms.dto

import java.io.Serializable

data class AuthenticatedUser(val user:String?,val role:String?): Serializable
