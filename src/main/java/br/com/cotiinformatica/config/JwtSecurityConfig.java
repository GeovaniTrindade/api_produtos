package br.com.cotiinformatica.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.filters.JwtSecurityFilter;

@Configuration
public class JwtSecurityConfig {

	@Bean
	public FilterRegistrationBean<JwtSecurityFilter> jwtFilter() {
		
		//registrando o filter que irá aplicar a validação de tokens para autenticação nos endpoints
		FilterRegistrationBean<JwtSecurityFilter> filter = new FilterRegistrationBean<JwtSecurityFilter>();
		filter.setFilter(new JwtSecurityFilter());
		
		//mapear os endpoints que exigem autenticação		
		filter.addUrlPatterns("/api/produtos/*");
		filter.addUrlPatterns("/api/movimentacoes/*");

		
		return filter;
	}	
}
