package com.github.knextsunj.cms.event

import org.springframework.context.ApplicationEvent

open class CustomerKYCEvent: ApplicationEvent {

    constructor(source:Any) : super(source)
}