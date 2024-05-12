package com.example.aiREG;

import com.example.aiREG.config.HintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
@ImportRuntimeHints(HintsRegistrar.class)
public class AiRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiRegApplication.class, args);
	}

}