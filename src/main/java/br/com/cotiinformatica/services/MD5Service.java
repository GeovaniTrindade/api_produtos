package br.com.cotiinformatica.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class MD5Service {

	public String encrypt(String value) {

		try {
			// Cria uma instância do algoritmo
			// de criptografia MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Converte o valor para um array de bytes
			byte[] valueBytes = value.getBytes();

			// Calcula o hash MD5 do valor
			byte[] digest = md.digest(valueBytes);

			// Converte o hash em uma representação hexadecimal
			BigInteger number = new BigInteger(1, digest);
			String md5Hash = number.toString(16);

			// Preenche com zeros à esquerda se necessário
			while (md5Hash.length() < 32) {
				md5Hash = "0" + md5Hash;
			}

			return md5Hash;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
