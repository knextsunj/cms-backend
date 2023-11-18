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
@Qualifier("customerJmsProducer")
open class CustomerJmsProducer : JmsProducer {

    companion object {
       private val logger: Logger = LoggerFactory.getLogger(CustomerJmsProducer::class.java)
    }

    @Autowired
    open lateinit var jmsTemplate: JmsTemplate;

    override fun sendMessage(customerId: String?) {
        logger.info("Sending customer data to queue:{}",customerId)
        val queue:Queue = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue("customer-id-kyc")
        jmsTemplate.convertAndSend(queue,customerId);

    }

    private fun logCustIdMessageSendFailure(customerId: String?, exception:RuntimeException) {
        logger.error("Failed to send message for customer id:{}",customerId)
    }

}