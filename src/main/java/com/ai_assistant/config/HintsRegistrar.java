package com.ai_assistant.config;

import com.ai_assistant.record.WeatherConfigProperties;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.shell.command.annotation.CommandScan;

@Configuration
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class HintsRegistrar  implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.resources().registerPattern("*.pdf");
        hints.resources().registerPattern("*.st");
    }
}