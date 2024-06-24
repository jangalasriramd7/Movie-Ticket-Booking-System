package com.BookMyShow.Ticket.theatre.Interceptors;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TheatreInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String method = requestTemplate.method();
            String url = requestTemplate.feignTarget().url();
            String path = requestTemplate.path();
            log.info("method: {}", method);
            log.info("url: {}", path);
            log.info("full url: {}{}", url, path);
        };
    }
}
