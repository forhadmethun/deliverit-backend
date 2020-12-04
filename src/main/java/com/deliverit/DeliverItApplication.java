package com.deliverit;

import com.deliverit.config.converter.LocalDateAdapter;
import com.deliverit.config.converter.LocalDateTimeAdapter;
import com.deliverit.config.converter.LocalTimeAdapter;
import com.google.gson.*;
import com.deliverit.utility.log.ApplicationStartupLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@SpringBootApplication
public class DeliverItApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext =
				SpringApplication.run(DeliverItApplication.class, args);
		Environment env = applicationContext.getEnvironment();
		ApplicationStartupLogger.logApplicationStartup(env);
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.serializeNulls()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
				.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter().nullSafe())
				.create();
	}

/*	@Bean
	public Gson gson() {
	return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
		@Override
		public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
			return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
		}
	}).create();*
}*/


}
