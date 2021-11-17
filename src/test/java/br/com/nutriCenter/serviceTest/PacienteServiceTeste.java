package br.com.nutriCenter.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.repository.PacienteRepository;
import br.com.nutriCenter.services.PacienteService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class PacienteServiceTeste {

	@TestConfiguration
	static class PacienteServiceTestConfiguration {

		@Bean
		public PacienteService pacienteService() {
			return new PacienteService();

		};

	}

	@Autowired
	PacienteService pacienteService;

	@MockBean
	PacienteRepository pacienteRepository;

	Paciente paciente;

	@BeforeEach
	public void setUp() throws Exception {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		paciente = new Paciente();

		paciente.setId(1L);
		paciente.setNome("Mateus");
		paciente.setSobreNome("Fernandes");
		paciente.setEmail("mateus@mateus.com");
		paciente.setGenero("Masculino");
		paciente.setNivelDeAcesso(1);
		paciente.setDataNasc(data);
		paciente.setCell("83 999475005");
		paciente.setBairro("Centro");
		paciente.setCidade("Monteiro");
		paciente.setEstado("Pb");
		paciente.setRua("Rua sem nome");
		paciente.setCpf("125.331.934-02");
		paciente.setDataCadastro(data);
		paciente.setDataNasc(data);
		paciente.setDataUltimaConsulta(data);

		pacienteService.create(paciente);

		Mockito.when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.ofNullable(paciente));

	}

	/* Teste do Buscar paciente Pelo ID */

	@Test
	public void buscarPacientePeloIdTeste() throws Exception {

		long id = 1L;

		Paciente paciente = pacienteService.findById(id).get();
		Assertions.assertEquals(paciente.getId(), id);

	}

	/*
	 * Teste do Deletar paciente Pelo ID
	 */

	@Test
	public void deletarNutricionistaPeloIdTeste() throws Exception {

		Optional<Paciente> pacienteEncontrado = pacienteService.findById(paciente.getId());
		pacienteService.deleteById(pacienteEncontrado.get().getId());
		assertFalse(!pacienteEncontrado.isPresent());
	}

	/*
	 * Teste do Deletar paciente
	 */

	@Test
	public void deletarObjetoPacienteTeste() throws Exception {

		Optional<Paciente> pacienteEncontrado = pacienteService.findById(paciente.getId());
		pacienteService.delete(pacienteEncontrado.get());
		var listPacientes = pacienteService.findAll();
		assertEquals(0, listPacientes.size());
	}

	/*
	 * Teste do Atualizar paciente Pelo ID
	 */

	/*
	@Test
	public void updatePeloIdPacientePeloIdTeste() throws Exception {

		long id = 1L;
		Paciente pacienteAtualizado;
		pacienteAtualizado = generatePaciente(3, "Brenda", "Scarlett", "brenda@email.com", "feminino", 1,
				"83 996247573", "Centro", "Monteiro", "PB", "Rua sem Nome", "758.323.010-13");

		Paciente pacienteAtualizar = pacienteService.findById(id).get();
		pacienteService.updateById(id, pacienteAtualizado);
		assertEquals(pacienteAtualizar.getNome(), pacienteAtualizado.getNome());

	}
	
	 */

	/*
	 * Metodo para gerar um paciente recebendo os atributos como parametros de
	 * entrada
	 */

	private Paciente generatePaciente(long id, String nome, String sobrenome, String email, String genero, int acesso,
			String cel, String bairro, String cidade, String estado, String rua, String cpf) throws ParseException {

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
		paciente.setDataCadastro(data);
		paciente.setDataNasc(data);
		paciente.setDataUltimaConsulta(data);

		return paciente;
	}

}
