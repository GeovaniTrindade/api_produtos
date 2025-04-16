package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**") // qualquer domínio
				.allowedOrigins("*") // qualquer domínio
				.allowedMethods("POST", "PUT", "DELETE", "GET") // métodos
				.allowedHeaders("*"); // parametros de cabeçalho
	}
}
