package com.Currency.Rate.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String API_KEY= "777c539a0e-0b609233a8-rlkumt";
    private static final String URL = "https://api.fastforex.io/fetch-one?from=USD&to=UAH&api_key=" + API_KEY;
    @Bean
    public WebClient getCurrency(){
        return WebClient.create(URL);
    }
}
