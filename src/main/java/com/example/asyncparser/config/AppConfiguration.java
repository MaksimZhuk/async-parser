package com.example.asyncparser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
@ComponentScan
public class AppConfiguration {
    @Bean
    public HttpClient httpClient(){
        return HttpClient.newBuilder().build();
    }
}
