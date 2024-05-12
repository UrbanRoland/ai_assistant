package com.ai_assistant.config;

import com.ai_assistant.record.WeatherConfigProperties;
import com.ai_assistant.record.WeatherRequest;
import com.ai_assistant.record.WeatherResponse;
import com.ai_assistant.service.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {
    
    private final WeatherConfigProperties props;
    
    public FunctionConfiguration(WeatherConfigProperties props) {
        this.props = props;
    }
    
    @Bean
    @Description("Get the current weather conditions for the given city.")
    public Function<WeatherRequest, WeatherResponse> currentWeatherFunction() {
        return new WeatherServiceImpl(props);
    }
}