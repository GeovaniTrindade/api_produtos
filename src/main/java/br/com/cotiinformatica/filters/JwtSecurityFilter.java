package br.com.cotiinformatica.filters;

import java.io.IOException;

import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtSecurityFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	        throws IOException, ServletException {

	    final HttpServletRequest request = (HttpServletRequest) servletRequest;
	    final HttpServletResponse response = (HttpServletResponse) servletResponse;

	    // Permitir CORS
	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
	    response.setHeader("Access-Control-Expose-Headers", "Authorization");
	    response.setHeader("Access-Control-Allow-Credentials", "true");

	    // Se for requisição OPTIONS, não processa o filtro
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        return;
	    }

	    // Validação de token
	    final String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
	        return;
	    }

	    try {
	        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	        Environment env = context.getEnvironment();
	        String jwtSecret = env.getProperty("jwt.secretkey");

	        final String token = authHeader.substring(7);
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();

	        request.setAttribute("claims", claims);
	        filterChain.doFilter(request, response);

	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
	    }
	}

}
