package br.com.cotiinformatica.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import br.com.cotiinformatica.services.MD5Service;

@Component
public class LoadData implements ApplicationRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MD5Service md5Service;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		usuarioRepository.save(new Usuario(1, "Geovani Trindade", "geovani@email.com", md5Service.encrypt("@Admin123")));
		usuarioRepository.save(new Usuario(2, "Ana Luiza", "ana@email.com", md5Service.encrypt("@Teste123")));
		usuarioRepository.save(new Usuario(3, "Lucas Fernandes", "lucas@email.com", md5Service.encrypt("@Teste123")));

	}
}
