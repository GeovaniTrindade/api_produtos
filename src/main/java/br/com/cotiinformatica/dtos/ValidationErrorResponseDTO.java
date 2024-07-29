package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class ValidationErrorResponseDTO {

	private String name;
	private String error;
}
