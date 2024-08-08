package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.AuthPostRequestDTO;
import br.com.cotiinformatica.dtos.AuthResponseDTO;
import br.com.cotiinformatica.dtos.MovimentacoesPostRequestDTO;
import br.com.cotiinformatica.dtos.ProdutosPostRequestDTO;
import br.com.cotiinformatica.dtos.ProdutosPutRequestDTO;
import br.com.cotiinformatica.dtos.ProdutosResponseDTO;
import br.com.cotiinformatica.entities.Produto;

@SpringBootTest
@AutoConfigureMockMvc // habilitando a biblioteca MockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiProdutosApplicationTests {

	@Autowired // executar as requisições para a API
	private MockMvc mockMvc;

	@Autowired // serializar e deserializar os dados enviados para API
	private ObjectMapper mapper;

	// atributo para guardar o produto cadastrado no teste
	private static Produto produto;

	// atributo para guardar o token gerado na autenticação
	private static String accessToken;

	@Test
	@Order(1)
	public void testAuthPost() throws Exception {

		AuthPostRequestDTO dto = new AuthPostRequestDTO();
		dto.setEmail("geovani@email.com");
		dto.setSenha("@Admin123");

		MvcResult result = mockMvc
				.perform(post("/api/auth").contentType("application/json").content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		AuthResponseDTO response = mapper.readValue(responseBody, AuthResponseDTO.class);

		accessToken = response.getAccessToken();
	}

	@Test
	@Order(1)
	public void testProdutosPost() throws Exception {
		Faker faker = new Faker();

		ProdutosPostRequestDTO dto = new ProdutosPostRequestDTO();
		dto.setNome(faker.commerce().productName());
		dto.setDescricao(faker.commerce().productName());
		dto.setPreco(Double.valueOf(faker.number().randomNumber(4, false)));
		dto.setQuantidade(faker.number().randomDigit());

		MvcResult result = mockMvc.perform(post("/api/produtos") // endpoint
				.contentType("application/json")
				// formato dos dados
				.content(mapper.writeValueAsString(dto)))
				// enviando os dados
				.andExpect(status().isCreated())
				// resultado esperado pelo teste
				.andReturn();
		// capturando o retorno dos dados da API

		// capturando os dados do produto
		// retornado pela API após o cadastro
		String responseBody = result.getResponse().getContentAsString();
		ProdutosResponseDTO response = mapper.readValue(responseBody, ProdutosResponseDTO.class);

		// capturando o id do produto
		produto = response.getProduto();
	}

	@Test
	@Order(2)
	public void testProdutosPut() throws Exception {

		Faker faker = new Faker();

		ProdutosPutRequestDTO dto = new ProdutosPutRequestDTO();
		dto.setIdProduto(produto.getIdProduto());
		dto.setNome(faker.commerce().productName());
		dto.setDescricao(faker.commerce().productName());
		dto.setPreco(Double.valueOf(faker.number().randomNumber(4, false)));
		dto.setQuantidade(faker.number().randomDigit());

		mockMvc.perform(put("/api/produtos") // endpoint
				.contentType("application/json")
				// formato dos dados
				.content(mapper.writeValueAsString(dto)))
				// enviando os dados
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	public void testProdutosGetAll() throws Exception {

		mockMvc.perform(get("/api/produtos")) // endpoint
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void testProdutosGetById() throws Exception {

		mockMvc.perform(get("/api/produtos/" + produto.getIdProduto())) // endpoint
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	public void testMovimentacoesPost() throws Exception {

		MovimentacoesPostRequestDTO dto = new MovimentacoesPostRequestDTO();
		dto.setIdProduto(produto.getIdProduto());
		dto.setDataMovimentacao("2024-07-30");
		dto.setObservacoes("Movimentação teste");
		dto.setQuantidade(10);
		dto.setTipo(1);

		mockMvc.perform(post("/api/movimentacoes") // endpoint
				.contentType("application/json")
				// formato dos dados
				.content(mapper.writeValueAsString(dto)))
				// enviando os dados
				.andExpect(status().isCreated());
	}

	@Test
	@Order(6)
	public void testMovimentacoesGetAll() throws Exception {

		mockMvc.perform(get("/api/movimentacoes/2024-07-01/2024-08-01"))
				// endpoint
				.andExpect(status().isOk());
	}

	@Test
	@Order(7)
	public void testProdutosDelete() throws Exception {

		testProdutosPost(); // criando um novo produto

		mockMvc.perform(delete("/api/produtos/" + produto.getIdProduto())) // endpoint
				.andExpect(status().isOk());
	}
}
