package com.ai_assistant.record;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * This class is a record class that holds the configuration properties for the weather service.
    Weather api https://www.weatherapi.com/api-explorer.aspx
 */
@ConfigurationProperties(value = "weather")
public record WeatherConfigProperties(String apiKey, String apiUrl) {
}