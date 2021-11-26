package br.com.nutriCenter.resourceTest;

import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

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
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.resource.NutricionistaResource;
import br.com.nutriCenter.services.NutricionistaService;

/**
 * @author José Caio
 * @author Mateus Fernandes
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NutricionistaResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NutricionistaResource nutricionistaController;

	@MockBean
	private NutricionistaService nutricionistaService;

	private Nutricionista nutricionista;

	private final String urlBase = "/api/nutricionista";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() throws ParseException {
		objectMapper = new ObjectMapper();
		nutricionista = generateNutricionista(1L, "mateus", "fernandes", "mateus@email.com", "masculino", 2,
				"22 2222222", "54675", "medico", 2, "mateus2020");

	}

	/* Test do GetAll de Nutricionistas */
	@Test
	public void deveRetornarSucessoAoListarTodosOsNutricionistasTest() throws Exception {
		mockMvc.perform(get(urlBase + "/getAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test Post persistir um novo objeto do tipo Nutricionista */
	@Test
	public void deveRetornarSucessoAoTentarAdicionarUmNovoObjetoTest() throws Exception {
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Teste do get por id de Nutricionista */
	@Test
	public void deveRetornarSucessoAoBuscarNutricionistaPeloIdTeste() throws Exception {

		when(this.nutricionistaService.findById(1L)).thenReturn(generateOptionalNutricionista(1L, "mateus", "fernandes",
				"mateus@email.com", "masculino", 2, "22 2222222", "54675", "medico", 2, "mateus2020"));
		mockMvc.perform(get(urlBase + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Teste do delete por id de Nutricionista */

	@Test
	public void deveRetornarSucessoQuandoDeletarNutricionista() throws Exception {
		mockMvc.perform(delete(urlBase + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test deletar um objeto do tipo Nutricionista */
	@Test

	public void deveRetornarSucessoAoTentarDeletarUmObjetoTest() throws Exception {
		mockMvc.perform(delete(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test do DeleteAll de Nutricionistas */
	@Test
	public void deveRetornarSucessoAoDeletarTodosOsNutricionistasTest() throws Exception {
		mockMvc.perform(delete(urlBase + "/delAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Teste do get por nome de Nutricionista */
	@Test
	public void deveRetornarSucessoAoBuscarNutricionistaPeloNomeTeste() throws Exception {

		when(this.nutricionistaService.findById(1L)).thenReturn(generateOptionalNutricionista(1L, "mateus", "fernandes",
				"mateus@email.com", "masculino", 2, "22 2222222", "54675", "medico", 2, "mateus2020"));

		mockMvc.perform(get(urlBase + "/byName/{nome}", "mateus").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	/* Test tentando cadastrar um nutricionista sem o atributo nome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemONomeTeste() throws Exception {

		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "", "fernandes", "mateus@email.com", "masculino", 2, "22 2222222",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um nutricionista sem o atributo Sobrenome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOSobreNomeTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "Mateus", "", "mateus@email.com", "masculino", 2, "22 2222222",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um nutricionista com o atributo email invalido */
	@Test
	public void deveRetornarErroAoTentarCadastrarComEmailInvalidoTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "Mateus", "Fernandes", "mateus.com", "masculino", 2, "22 2222222",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	/* Test tentando cadastrar um nutricionista sem o atributo genero */
	@Test
	public void deveRetornarErroAoTentarCadastrarComGeneroNulloTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "Mateus", "Fernandes", "mateus@email.com", "", 2, "22 2222222",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um nutricionista sem o atributo telefone */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOTelefoneTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "Mateus", "Fernandes", "mateus@email.com", "Masculino", 2, "",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	/*
	 * Test tentando cadastrar um nutricionista com um nome invalido Falta Corrigir
	 */
	@Test
	public void deveRetornarErroAoTentarCadastrarComNomeInvalidoTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, "22 2222222",
				"54675", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um nutricionista sem o atributo crn */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOCrnTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, "22 2222222",
				"", "medico", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/*
	 * Test tentando cadastrar um nutricionista sem o atributo especialidade * Falta
	 * corrigir
	 */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemEspecialidadeTeste() throws Exception {
		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, "22 2222222",
				"54321", "", 2, "mateus2020");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um Nutricionista com senha nula */
	@Test
	public void deveRetornarErroAoTentarCadastarSemSenha() throws Exception {

		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, "22 2222222",
				"54321", "", 2, "");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	/*
	 * Test tentando cadastrar um Nutricionista com uma senha menor que 8 caracteres
	 */
	@Test
	public void deveRetornarErroAoTentarCadastarComSenhaInvalida() throws Exception {

		Nutricionista nutricionista;
		nutricionista = generateNutricionista(1L, "M", "Fernandes", "mateus@email.com", "Masculino", 2, "22 2222222",
				"54321", "", 2, "mateus");
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(nutricionista))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	private Optional<Nutricionista> generateOptionalNutricionista(long id, String nome, String sobrneome, String email,
			String genero, int carga, String cell, String crn, String especialidade, int nivel, String senha)
			throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		Nutricionista nutri = new Nutricionista();
		nutri.setId(id);
		nutri.setNome(nome);
		nutri.setSobreNome(sobrneome);
		nutri.setEmail(email);
		nutri.setGenero(genero);
		nutri.setCargaHoraria(carga);
		nutri.setCell(cell);
		nutri.setCRN(crn);
		nutri.setDataNasc(data);
		nutri.setEspecialidade(especialidade);
		nutri.setNivelDeAcesso(nivel);
		nutri.setEspecializacoes(null);
		nutri.setSenha(email);
		nutri.setSenha(senha);
		return Optional.ofNullable(nutri);

	}

	private Nutricionista generateNutricionista(long id, String nome, String sobrneome, String email, String genero,
			int carga, String cell, String crn, String especialidade, int nivel, String senha) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		Nutricionista nutri = new Nutricionista();
		nutri.setId(id);
		nutri.setNome(nome);
		nutri.setSobreNome(sobrneome);
		nutri.setEmail(email);
		nutri.setGenero(genero);
		nutri.setCargaHoraria(carga);
		nutri.setCell(cell);
		nutri.setCRN(crn);
		nutri.setDataNasc(data);
		nutri.setEspecialidade(especialidade);
		nutri.setNivelDeAcesso(nivel);
		nutri.setEspecializacoes(null);

		nutri.setSenha(email);
		nutri.setSenha(senha);
		return nutri;

	}
}
