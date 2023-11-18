package com.github.knextsunj.cms.event

import org.springframework.context.ApplicationEvent

open class AddressKYCEvent: ApplicationEvent {

    constructor(source:Any) : super(source)
}