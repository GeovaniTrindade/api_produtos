package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		/* OK
		 *
		 * registry.addMapping("/**") .allowedOrigins("http://localhost:4200")
		 * .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		 * .allowedHeaders("*") .allowCredentials(true);
		 *  
		 */
		
		
		// OK
		  registry.addMapping("/api/auth") .allowedOrigins("*") .allowedMethods("POST")
		  .allowedHeaders("*");
		  
		  registry.addMapping("/api/produtos") .allowedOrigins("*")
		  .allowedMethods("POST", "PUT", "DELETE", "GET") .allowedHeaders("*");
		  
		  registry.addMapping("/api/movimentacoes") .allowedOrigins("*")
		  .allowedMethods("POST", "PUT", "DELETE", "GET") .allowedHeaders("*");
		 
	}

}
