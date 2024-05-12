package com.ai_assistant.service;

import com.ai_assistant.record.WeatherConfigProperties;
import com.ai_assistant.record.WeatherRequest;
import com.ai_assistant.record.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

public class WeatherServiceImpl implements Function<WeatherRequest, WeatherResponse> {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final WeatherConfigProperties weatherConfigProperties;
    private final RestClient restClient;
    
    public WeatherServiceImpl(WeatherConfigProperties weatherConfigProperties) {
        this.weatherConfigProperties = weatherConfigProperties;
        this.restClient = RestClient.create(weatherConfigProperties.apiUrl());
    }
    
    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        logger.info("Weather Request: {}", weatherRequest);
        WeatherResponse response = restClient.get()
            .uri("/current.json?key={key}&q={q}", weatherConfigProperties.apiKey(), weatherRequest.city())
            .retrieve()
            .body(WeatherResponse.class);
        logger.info("Weather API Response: {}", response);
        return response;
    }
}