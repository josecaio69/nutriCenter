package br.com.nutriCenter.resourceTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.resource.AdministradorResource;
import br.com.nutriCenter.services.AdministradorService;

/**
 * @author Mateus Fernandes
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdministradorResourceTeste {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdministradorResource administradorResource;

	@MockBean
	private AdministradorService administradorService;

	private Administrador administrador;

	private final String urlBase = "/api/admin";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() throws ParseException {

		objectMapper = new ObjectMapper();
		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "Fernandes", "mateus@email.com", "Masculino", 2, 2,
				"999475005", "PJ2-2020");

	}

	/* Test do GetAll de Administradores */
	@Test
	public void deveRetornarSucessoAoListarTodosOsAdministradores() throws Exception {
		mockMvc.perform(get(urlBase + "/getAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/* Teste do get por id de Administrador */
	@Test
	public void deveRetornarsucessoAoBuscarAdministradorPeloIdTeste() throws Exception {
		when(this.administradorService.findById(1L)).thenReturn(generateOptionalAdministrador(1L, "Joao", "Paulo",
				"Joao@email.com", "Masculino", 1, 1, "999475005", "PJ2-2020"));
		mockMvc.perform(get(urlBase + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test Post inserir um novo Administrador */
	@Test
	public void deveRetornarSucessoAoTentarAdicionarUmNovoAdministradorTest() throws Exception {

		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "Fernandes", "mateus@email.com", "Masculino", 2, 2,
				"999475005", "PJ2-2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Teste do delete por id de Administrador */
	@Test
	public void deveRetornarSucessoAoDeletarAdministradorPeloIdTeste() throws Exception {
		mockMvc.perform(delete(urlBase + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/* Test deletar um objeto do tipo Admistrador */
	@Test

	public void deveRetornarSucessoAoTentarDeletarAdministradorTest() throws Exception {
		mockMvc.perform(delete(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test tentando cadastrar um administrador sem o atributo nome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemONomeTeste() throws Exception {

		Administrador administrador;
		administrador = generateAdministrador(1L, "", "Fernandes", "mateus@email.com", "Masculino", 2, 2, "999475005",
				"PJ2-2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um administrador sem o atributo Sobrenome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOSobreNomeTeste() throws Exception {

		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "", "mateus@email.com", "Masculino", 2, 2, "999475005",
				"PJ2-2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um administrador com o atributo email invalido */
	@Test
	public void deveRetornarErroAoTentarCadastrarComEmailInvalidoTeste() throws Exception {
		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "", "mateus.com", "Masculino", 2, 2, "999475005",
				"PJ2-2020");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um administrador sem o atributo genero */
	@Test
	public void deveRetornarErroAoTentarCadastrarComGeneroNulloTeste() throws Exception {
		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "Fernandes", "mateus@email.com", "", 2, 2, "999475005",
				"PJ2-2020");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um administrador sem o atributo telefone */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOTelefoneTeste() throws Exception {
		Administrador administrador;
		administrador = generateAdministrador(1L, "Mateus", "Fernandes", "mateus@email.com", "", 2, 2, "", "PJ2-2020");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}


	/* Test tentando cadastrar um administrador com um nome invalido */
	@Test
	public void deveRetornarErroAoTentarCadastrarComNomeInvalidoTeste() throws Exception {
		Administrador administrador;
		administrador = generateAdministrador(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, 2, "999475005",
				"PJ2-2020");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um administrador com senha nula */
	@Test
	public void deveRetornarErroAoTentarCadastarSemSenha() throws Exception {

		Administrador administrador;
		administrador = generateAdministrador(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, 2, "999475005",
				"");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	/*
	 * Test tentando cadastrar um administrador com uma senha menor que 8 caracteres
	 */
	@Test
	public void deveRetornarErroAoTentarCadastarComSenhaInvalida() throws Exception {

		Administrador administrador;
		administrador = generateAdministrador(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, 2, "999475005",
				"1234");

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	private Optional<Administrador> generateOptionalAdministrador(long id, String nome, String sobrenome, String email,
			String genero, int carga, int acesso, String cel, String senha) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		administrador = new Administrador();

		administrador.setId(id);
		administrador.setNome(nome);
		administrador.setSobreNome(sobrenome);
		administrador.setEmail(email);
		administrador.setGenero(genero);
		administrador.setCargaHoraria(carga);
		administrador.setNivelDeAcesso(acesso);
		administrador.setDataNasc(data);
		administrador.setCell(cel);
		administrador.setSenha(email);	
		administrador.setSenha(senha);

		return Optional.ofNullable(administrador);
	}

	private Administrador generateAdministrador(long id, String nome, String sobrenome, String email, String genero,
			int carga, int acesso, String cel, String senha) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		administrador = new Administrador();

		administrador.setId(id);
		administrador.setNome(nome);
		administrador.setSobreNome(sobrenome);
		administrador.setEmail(email);
		administrador.setGenero(genero);
		administrador.setCargaHoraria(carga);
		administrador.setNivelDeAcesso(acesso);
		administrador.setDataNasc(data);
		administrador.setCell(cel);
		administrador.setSenha(email);
		administrador.setSenha(senha);


		return administrador;
	}

}
