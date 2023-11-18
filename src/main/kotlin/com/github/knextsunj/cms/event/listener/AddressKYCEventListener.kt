package com.github.knextsunj.cms.event.listener

import com.github.knextsunj.cms.event.AddressKYCEvent
import com.github.knextsunj.cms.service.messaging.JmsProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.event.EventListener
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
open class AddressKYCEventListener {

    @Autowired
    @Qualifier("addressJmsProducer")
    open lateinit var jmsProducer: JmsProducer

    @Autowired
    open lateinit var mappingJackson2HttpMessageConverter: MappingJackson2HttpMessageConverter


    @EventListener
    @Async
    fun handleEvent(addressKYCEvent: AddressKYCEvent) {
        val message = mappingJackson2HttpMessageConverter.objectMapper.writeValueAsString(addressKYCEvent)
        jmsProducer.sendMessage(message)
    }
}