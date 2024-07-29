package br.com.cotiinformatica.dtos;

import org.springframework.http.HttpStatus;

import br.com.cotiinformatica.entities.Produto;
import lombok.Data;

@Data
public class ProdutosResponseDTO {


	private HttpStatus status;
	private String mensagem;
	private Produto produto;
}
