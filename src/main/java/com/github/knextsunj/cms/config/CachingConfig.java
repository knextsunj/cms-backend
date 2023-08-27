package com.github.knextsunj.cms.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CachingConfig  {

    @Bean
    public HazelcastInstance hazelcastInstance() {


        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("dev");

        ClientNetworkConfig clientNetworkConfig = clientConfig.getNetworkConfig();
       clientNetworkConfig.addAddress("<ip1>:5701","<ip2>:5701")
                .addAddress("34700-34710")
                .setRedoOperation(true)
                .setConnectionTimeout(5000);

        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
       return hazelcastInstance;
    }

}
