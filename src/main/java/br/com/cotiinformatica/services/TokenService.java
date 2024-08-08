package br.com.cotiinformatica.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	// atributo que captura o valor de uma configuração
	// mapeada no arquivo /application.properties
	@Value("${jwt.secretkey}")
	private String jwtSecretKey;

	public String generateToken(String emailUsuario) {

		return Jwts.builder().setSubject(emailUsuario)
				// gravando o email do usuário no token
				.setIssuedAt(new Date())
				// data de geração do token
				.signWith(SignatureAlgorithm.HS256, jwtSecretKey) // chave antifalsificação
				.compact(); // gerando e retornando o token
	}
}
