package com.github.knextsunj.cms.event.listener

import com.github.knextsunj.cms.event.CustomerKYCEvent
import com.github.knextsunj.cms.service.messaging.JmsProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.event.EventListener
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.apache.commons.lang3.tuple.Pair;

@Component
open class CustomerKYCEventListener {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CustomerKYCEventListener::class.java)
    }

    @Autowired
    @Qualifier("customerJmsProducer")
    open lateinit var jmsProducer: JmsProducer

    @Autowired
    open lateinit var mappingJackson2HttpMessageConverter: MappingJackson2HttpMessageConverter

    @EventListener
    @Async
    fun handleEvent(customerKYCEvent: CustomerKYCEvent) {
        var pair:Pair<String,String> = customerKYCEvent.source as Pair<String, String>
        val message = mappingJackson2HttpMessageConverter.objectMapper.writeValueAsString(pair)
        logger.info("Customer message to be sent to Customer queue:{}",message)
        jmsProducer.sendMessage(message)
    }

}