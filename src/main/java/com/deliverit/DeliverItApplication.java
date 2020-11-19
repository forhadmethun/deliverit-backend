package com.deliverit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.deliverit.utility.log.ApplicationStartupLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DeliverItApplication {

	@Bean
	public Gson gson() {
		return new GsonBuilder().serializeNulls().create();
	}

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext =
				SpringApplication.run(DeliverItApplication.class, args);
		Environment env = applicationContext.getEnvironment();
		ApplicationStartupLogger.logApplicationStartup(env);
	}

}
