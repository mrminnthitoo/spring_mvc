package com.minnthitoo.spring_mvc.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebClientConfig implements WebMvcConfigurer {
    @Value("${todo.baseURL}")
    String base_url;

    @Value("${webclient.connecttimeout}")
    Long connectTimeout;

    @Value("${webclient.sockettimeout}")
    Long socketTimeout;

    @Value("${webclient.requesttimeout}")
    Long requestTimeout;

    @Value("${webclient.readtimeout}")
    Long readTimeout;

    @Bean
    RestClient restClient(){
        return RestClient.builder()
                .requestFactory(getClientHttpRequestFactory())
                .baseUrl(base_url)
                .build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory(){
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(100);
//        clientHttpRequestFactory.setConnectionRequestTimeout(70);
//        return clientHttpRequestFactory;

        /*
        ** this simplefactory didn't contain connection pool
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(300);
        factory.setReadTimeout(3000);
        return factory;

         */
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofMicroseconds(connectTimeout ))
                .build();

        // socket timeout, read timeout
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(socketTimeout))
                .build();

        // connection request timeout
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(requestTimeout))
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(socketConfig);
        connectionManager.setDefaultConnectionConfig(connectionConfig);

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new  HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(Duration.ofMillis(readTimeout));
        factory.setConnectTimeout(Math.toIntExact(connectTimeout));
        return factory;

    }
}
