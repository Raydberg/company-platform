package com.company.platform.msvcreportms.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBean {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
