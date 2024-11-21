package com.seyidahmetarvas.userservice.config;

import com.seyidahmetarvas.userservice.client.RetrieveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignErrorConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new RetrieveMessageErrorDecoder();
    }
}
