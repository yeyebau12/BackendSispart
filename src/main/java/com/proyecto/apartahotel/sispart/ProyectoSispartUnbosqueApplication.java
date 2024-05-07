package com.proyecto.apartahotel.sispart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProyectoSispartUnbosqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSispartUnbosqueApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("https://apartahotelsantandereano.web.app", "http://localhost:3000",
								"http://localhost:4200", "http://192.168.1.15:3000").allowCredentials(true)
						.allowedMethods("*").allowedHeaders("*");
			}
		};
	}
}
