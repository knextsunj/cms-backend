package com.github.knextsunj.cms.config;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@Configuration
public class ObjectStorageClientConfig {

    private static final Logger logger = LogManager.getLogger(ObjectStorageClientConfig.class);

    String configurationFilePath = "config";
    String profile = "DEFAULT";

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ObjectStorage objectStorage() throws IOException {

        ObjectStorage objectStorage = null;

        //load config file
        try {
            final ConfigFileReader.ConfigFile
                    configFile = ConfigFileReader
                    .parse(configurationFilePath, profile);

            final ConfigFileAuthenticationDetailsProvider provider =
                    new ConfigFileAuthenticationDetailsProvider(configFile);

            //build and return client
            objectStorage = ObjectStorageClient.builder()
                    .build(provider);
        } catch (IOException ioException) {
            logger.error("Failed to obtain OCI ObjectStorage instance", ioException);
        }
        return objectStorage;
    }

}

