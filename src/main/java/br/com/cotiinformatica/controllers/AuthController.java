package br.com.cotiinformatica.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AuthPostRequestDTO;
import br.com.cotiinformatica.dtos.AuthResponseDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import br.com.cotiinformatica.services.MD5Service;
import br.com.cotiinformatica.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MD5Service md5Service;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<AuthResponseDTO> post(@RequestBody @Valid AuthPostRequestDTO dto) {

		AuthResponseDTO response = new AuthResponseDTO();

		try {

			// presquisar o usuário no banco de dados através do email e senha
			Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(),
					md5Service.encrypt(dto.getSenha()));

			// verificando se o usuário foi encontrado
			if (usuario.isPresent()) {
				Usuario item = usuario.get();

				response.setStatus(HttpStatus.OK);
				response.setMensagem("Usuário autenticado com sucesso!");
				response.setNomeUsuario(item.getNome());
				response.setEmailUsuario(item.getEmail());
				response.setAccessToken(tokenService.generateToken(item.getEmail()));

			} else {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				response.setMensagem("Acesso negado. Usuário não autorizado!");
			}

		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());

		}

		return ResponseEntity.status(response.getStatus()).body(response);

	}
}
