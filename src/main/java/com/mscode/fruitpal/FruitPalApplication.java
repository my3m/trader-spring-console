package com.mscode.fruitpal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FruitPalApplication {
	private static Logger LOG = LoggerFactory
			.getLogger(FruitPalApplication.class);
	public static void main(String[] args) {
		LOG.info("APPLICATION STARTED");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(FruitPalApplication.class, args);
		//FruitPalCommandLineRunner.run(configurableApplicationContext);
		LOG.info("APPLICATION ENDED");
	}
}
