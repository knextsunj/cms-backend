package com.github.knextsunj.cms.config.security.model

import javax.security.enterprise.credential.Credential

open class JWTCredential: Credential {

    var principal:String? = null;
    var authorities:MutableSet<String>? = null;

    constructor(principal:String?,authorities:MutableSet<String>?) {
        this.principal = principal;
        this.authorities = authorities;
    }

}