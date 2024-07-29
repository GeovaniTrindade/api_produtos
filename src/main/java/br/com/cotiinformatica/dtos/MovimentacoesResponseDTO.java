package br.com.cotiinformatica.dtos;

import org.springframework.http.HttpStatus;

import br.com.cotiinformatica.entities.Movimentacao;
import lombok.Data;

@Data
public class MovimentacoesResponseDTO {

	private HttpStatus status;
	private String mensagem;
	private Movimentacao movimentacao;
}
