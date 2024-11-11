package com.minnthitoo.spring_mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebClientConfig implements WebMvcConfigurer {
    @Value("${todo.baseURL}")
    String base_url;
    @Bean
    RestClient restClient(){
        return RestClient.builder()
                .requestFactory(getClientHttpRequestFactory())
                .baseUrl(base_url)
                .build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(100);
        clientHttpRequestFactory.setConnectionRequestTimeout(70);
        return clientHttpRequestFactory;
    }
}
