package io.mohajistudio.tangerine.prototype;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().description("MohajiStudio, 4th project prototype blog service").title("Tengerine Prototype").version("v0.0.1");
		return new OpenAPI().info(info);
	}
}
