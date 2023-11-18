package com.github.knextsunj.cms.event.publisher

import com.github.knextsunj.cms.event.CustomerKYCEvent
import org.apache.commons.lang3.tuple.Pair
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
open class CustomerKYCEventPublisher {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CustomerKYCEventPublisher::class.java)
    }

    @Autowired
    val applicationEventPublisher: ApplicationEventPublisher? = null

    fun publishCustomerKYCEvent(pair:Pair<String,String>):String {
        logger.info("data published for customer event:{}",pair)
        var customerKYCEvent = CustomerKYCEvent(pair)
        applicationEventPublisher?.publishEvent(customerKYCEvent)
        return "CUSTOMER_ID_PUBLISHED"
    }
}