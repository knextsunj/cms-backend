package com.github.knextsunj.cms.event.publisher

import com.github.knextsunj.cms.event.AddressKYCEvent
import org.apache.commons.lang3.tuple.Pair
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
open class AddressKYCEventPublisher {

    @Autowired
    val applicationEventPublisher: ApplicationEventPublisher? = null

    fun publishAdressKYCEvent(message: Pair<String, String>):String {
        var addressKYCEvent = AddressKYCEvent(message)
        applicationEventPublisher?.publishEvent(addressKYCEvent)
        return "ADDRESS_ID_PUBLISHED"
    }
}