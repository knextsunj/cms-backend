package com.github.knextsunj.cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.jersey.JerseyServerMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication( exclude = {ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class} )
@SpringBootApplication(exclude = JerseyServerMetricsAutoConfiguration.class)
@EnableCaching
@EnableTransactionManagement
@EnableAsync
//@EnableJms
public class CmsApplication extends SpringBootServletInitializer {

		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(CmsApplication.class);

		}
	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
