package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProdutosPostRequestDTO {

	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü0-9\\s]{8,150}$", message = "Por favor, informe um nome válido de 8 a 150 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do produto.")
	private String nome;

	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü0-9\\s]{8,150}$", message = "Por favor, informe um nome válido de 8 a 150 caracteres.")
	@NotBlank(message = "Por favor, informe a descrição do produto.")
	private String descricao;

	@Min(value = 1, message = "O preço deve ser maior ou igual a 1.")
	private Double preco;

	@Min(value = 1, message = "Quantidade deve ser maior ou igual a 1.")
	private Integer quantidade;
}
