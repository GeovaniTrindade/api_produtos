package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthPostRequestDTO {

	@NotBlank(message = "Por favor, informe o email do usuário.")
	@Email(message = "Por favor, informe um endereço de email válido.")
	private String email;

	@NotBlank(message = "Por favor, informe a senha de acesso do usuário.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
			 message = "Por favor, informe uma senha forte com no mínimo 8 caracteres.")
	private String senha;
}
