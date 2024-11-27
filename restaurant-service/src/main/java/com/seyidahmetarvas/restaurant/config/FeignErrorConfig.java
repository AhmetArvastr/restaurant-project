package com.seyidahmetarvas.restaurant.config;

import com.seyidahmetarvas.restaurant.client.RetrieveMessageErrorDecoder;
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
