package com.github.knextsunj.cms.service.messaging.impl

import com.github.knextsunj.cms.service.messaging.JmsProducer
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import javax.jms.Queue

@Component
@Qualifier("addressJmsProducer")
open class AddressJmsProducer : JmsProducer {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AddressJmsProducer::class.java)
    }


    @Autowired
    open lateinit var jmsTemplate: JmsTemplate;

     override fun sendMessage(addressId: String?) {
        val queue: Queue = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue("address-id-kyc")
        jmsTemplate.convertAndSend(queue,addressId);
    }

    private fun logAddrIdMessageSendFailure(addressId: String?, exception:RuntimeException) {
        logger.error("Failed to send message for address id:{}",addressId)
    }

}