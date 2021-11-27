package br.com.nutriCenter.resourceTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.resource.PacienteResource;
import br.com.nutriCenter.services.PacienteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PacienteResourceTeste {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PacienteResource pacienteResource;

	@MockBean
	private PacienteService pacienteService;

	private Paciente paciente;

	private final String urlBase = "/api/paciente";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() throws ParseException {

		objectMapper = new ObjectMapper();
		Paciente paciente1;
		paciente1 = generatePaciente(1L, "Jose", "Caio", "jose@email.com", "Masculino", 2, 1, "99 9999-9999", "Centro",
				"Sume", "PB", "Rua sem Nome", "125.331.934-02", "maria2020", true);

	}

	/* Test do GetAll de Pacientes */
	@Test
	public void deveRetornarSucessoAoListarTodosOsPaciente() throws Exception {
		mockMvc.perform(get(urlBase + "/getAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/* Teste do get por id de Pacientes */
	@Test
	public void deveRetornarsucessoAoBuscarPacientesPeloIdTeste() throws Exception {
		when(this.pacienteService.findById(1L))
				.thenReturn(generateOptionalPaciente(1L, "Jose", "Caio", "jose@email.com", "Masculino", 2, 1,
						"99 9999-9999", "Centro", "Sume", "PB", "Rua sem Nome", "125.331.934-02", "maria2020", true));
		mockMvc.perform(get(urlBase + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test Post inserir um novo Paciente */
	@Test
	public void deveRetornarSucessoAoTentarAdicionarUmNovoPacienteTest() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(2L, "Maria", "Da Silva", "maria@email.com", "feminino", 2, 1, "83 98672-5449",
				"Centro", "Sume", "PB", "Rua sem Nome", "481.715.130-77", "maria2020", true);

		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test tentando cadastrar um paciente sem o atributo nome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemONomeTeste() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "", "Silva", "maria@email.com", "feminino", 2, 1, "99 9999-9999", "Centro",
				"Sume", "PB", "Rua sem Nome", "064.765,876-09", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um paciente sem o atributo Sobrenome */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOSobreNomeTeste() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "", "maria@email.com", "feminino", 2, 1, "99 9999-9999", "Centro",
				"Sume", "PB", "Rua sem Nome", "064.765,876-09", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um paciente com o atributo email invalido */
	@Test
	public void deveRetornarErroAoTentarCadastrarComEmailInvalidoTeste() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria.com", "feminino", 2, 1, "99 9999-9999", "Centro",
				"Sume", "PB", "Rua sem Nome", "064.765,876-09", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um paciente sem o atributo genero */
	@Test
	public void deveRetornarErroAoTentarCadastrarComGeneroNulloTeste() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "", 2, 1, "99 9999-9999", "Centro", "Sume",
				"PB", "Rua sem Nome", "064.765,876-09", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um paciente sem o atributo telefone */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOTelefoneTeste() throws Exception {
		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "", "Centro", "Sume",
				"PB", "Rua sem Nome", "064.765,876-09", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/*
	 * Test tentando cadastrar um paciente com uma senha menor que 8 caracteres
	 */
	@Test
	public void deveRetornarErroAoTentarCadastarComSenhaInvalida() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "", "Centro", "Sume",
				"PB", "Rua sem Nome", "064.765,876-09", "1234567", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/*
	 * Test tentando cadastrar um paciente com uma senha de 8 caracteres
	 */
	@Test
	public void deveRetornarSucessoAoCadastrarComSenhaDeOitoCaracteres() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "83999475005", "Centro", "Sume",
				"PB", "Rua sem Nome", "125.331.934-02", "maria2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/* Test tentando cadastrar um paciente com senha nula */
	@Test
	public void deveRetornarErroAoTentarCadastarSemSenha() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "", "Centro", "Sume",
				"PB", "Rua sem Nome", "064.765,876-09", "", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/* Test tentando cadastrar um paciente com o cpf nulo */
	@Test
	public void deveRetornarErroAoTentarCadastrarSemOCpf() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "", "Centro", "Sume",
				"PB", "Rua sem Nome", "", "PJ2-2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	/* Test tentando cadastrar um paciente com o cpf invalido */
	@Test
	public void deveRetornarErroAoTentarCadastrarComOCpfInvalido() throws Exception {

		Paciente paciente;
		paciente = generatePaciente(1L, "Maria", "Silva", "maria@email.com", "feminino", 2, 1, "", "Centro", "Sume",
				"PB", "Rua sem Nome", "111.111.111-11", "PJ2-2020", true);
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(paciente))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	private Optional<Paciente> generateOptionalPaciente(long id, String nome, String sobrenome, String email,
			String genero, int carga, int acesso, String cel, String bairro, String cidade, String estado, String rua,
			String cpf, String senha, boolean status) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		paciente = new Paciente();

		paciente.setId(id);
		paciente.setNome(nome);
		paciente.setSobreNome(sobrenome);
		paciente.setEmail(email);
		paciente.setGenero(genero);
		paciente.setNivelDeAcesso(acesso);
		paciente.setDataNasc(data);
		paciente.setCell(cel);
		paciente.setBairro(bairro);
		paciente.setCidade(cidade);
		paciente.setEstado(estado);
		paciente.setRua(rua);
		paciente.setCpf(cpf);
		paciente.setDataNasc(data);
		paciente.setDataUltimaConsulta(data);
		paciente.setSenha(cpf);
		paciente.setSenha(senha);
		paciente.setStatus(status);
		return Optional.ofNullable(paciente);
	}

	private Paciente generatePaciente(long id, String nome, String sobrenome, String email, String genero, int carga,
			int acesso, String cel, String bairro, String cidade, String estado, String rua, String cpf, String senha, boolean status)
			throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		paciente = new Paciente();

		paciente.setId(id);
		paciente.setNome(nome);
		paciente.setSobreNome(sobrenome);
		paciente.setEmail(email);
		paciente.setGenero(genero);
		paciente.setNivelDeAcesso(acesso);
		paciente.setDataNasc(data);
		paciente.setCell(cel);
		paciente.setBairro(bairro);
		paciente.setCidade(cidade);
		paciente.setEstado(estado);
		paciente.setRua(rua);
		paciente.setCpf(cpf);
		paciente.setSenha(senha);
		paciente.setDataCadastro(data);
		paciente.setDataNasc(data);
		paciente.setDataUltimaConsulta(data);
		paciente.setSenha(cpf);
		paciente.setStatus(status);
		return paciente;
	}

}
