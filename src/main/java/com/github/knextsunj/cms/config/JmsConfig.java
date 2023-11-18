//package com.github.knextsunj.cms.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.destination.JndiDestinationResolver;
//import org.springframework.jndi.JndiObjectFactoryBean;
//
//import javax.annotation.Priority;
//import javax.jms.ConnectionFactory;
//import javax.jms.Queue;
//import javax.jms.QueueConnectionFactory;
//import javax.naming.Context;
//import javax.naming.NamingException;
//import java.util.Properties;
//
//import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
//
//@Configuration
//public class JmsConfig {
//
//    private static final String CONNECTION_FACTORY = "java:/ActiveMQ/QueueConnectionFactory";
//
//    private static  final String CUSTOMER_QUEUE = "java:/queue/CustomerIdKyc";
//
//    private static final String ADDRESS_QUEUE = "java:/queue/AddressIdKyc";
//
//
//   @Bean
//    public ConnectionFactory connectionFactory() throws NamingException {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName(CONNECTION_FACTORY);
//        jndiObjectFactoryBean.setJndiEnvironment(getEnvProperties());
//        jndiObjectFactoryBean.afterPropertiesSet();
//        return (QueueConnectionFactory) jndiObjectFactoryBean.getObject();
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() throws NamingException {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        return jmsTemplate;
//    }
//
//    @Bean
//    public Queue customerQueue() throws NamingException {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName(CUSTOMER_QUEUE);
//        jndiObjectFactoryBean.setJndiEnvironment(getEnvProperties());
//        jndiObjectFactoryBean.afterPropertiesSet();
//        return (Queue) jndiObjectFactoryBean.getObject();
//    }
//
//    @Bean
//    public Queue addressQueue() throws NamingException {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName(ADDRESS_QUEUE);
//        jndiObjectFactoryBean.setJndiEnvironment(getEnvProperties());
//        jndiObjectFactoryBean.afterPropertiesSet();
//        return (Queue) jndiObjectFactoryBean.getObject();
//    }
//
//    Properties getEnvProperties() {
//        Properties env = new Properties();
//        env.put(INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
//        env.put(Context.PROVIDER_URL, "<ipaddr>?soTimeout=20000&amp;connectionTimeout=10000");
//        env.put(Context.SECURITY_PRINCIPAL, "system");
//        env.put(Context.SECURITY_CREDENTIALS, "manager");
//        return env;
//    }
//}
