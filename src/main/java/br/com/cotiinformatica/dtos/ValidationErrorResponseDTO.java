package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class ValidationErrorResponseDTO {

	private String nome;
	private String error;
}
