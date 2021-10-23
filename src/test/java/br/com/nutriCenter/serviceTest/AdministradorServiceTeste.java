package br.com.nutriCenter.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import br.com.nutriCenter.repository.AdministradorRepository;
import br.com.nutriCenter.services.AdministradorService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class AdministradorServiceTeste {

	@TestConfiguration
	static class AdministradorServiceTestConfiguration {

		@Bean
		public AdministradorService administradorService() {
			return new AdministradorService();
		};

	}

	@Autowired
	AdministradorService administradorService;

	@MockBean
	AdministradorRepository administradorRepository;

	Administrador admin;

	@BeforeEach
	public void setUp() throws Exception {

		admin = new Administrador();
		admin.setCargaHoraria(1);
		admin.setCell(null);
		admin.setCpf("555-555-555-55");
		admin.setDataNasc(null);
		admin.setEmail("maria@email.com");
		admin.setGenero("Feminino");
		admin.setId(1L);
		admin.setNivelDeAcesso(3);
		admin.setNome("Maria");
		admin.setSobreNome("Alves");

		administradorService.create(admin);

		Mockito.when(administradorRepository.findById(admin.getId())).thenReturn(Optional.ofNullable(admin));
	}

	/* Teste do Buscar Administrador Pelo ID */

	@Test
	public void buscarAdministradorPeloIdTeste() throws Exception {

		long id = 1L;

		Administrador administrador = administradorService.findById(id).get();
		Assertions.assertEquals(administrador.getId(), id);

	}

	/*
	 * Teste do Deletar Nutricionista Pelo ID Está com erro
	 */

	@Test
	public void deletarNutricionistaPeloIdTeste() throws Exception {

		Optional<Administrador> administrador = administradorService.findById(admin.getId());
		administradorService.deleteById(administrador.get().getId());
		assertFalse(!administrador.isPresent());
	}

	/*
	 * Teste do Deletar administrador
	 */

	@Test
	public void deletarObjetoAdministradorTeste() throws Exception {

		Optional<Administrador> administrador = administradorService.findById(admin.getId());
		administradorService.delete(administrador.get());
		var listAdmin = administradorService.findAll();
		assertEquals(0, listAdmin.size());
	}

	/*
	 * Teste do Atualizar Administrador Pelo ID
	 */

	@Test
	public void updatePeloIdAdministradorPeloIdTeste() throws Exception {

		long id = 1L;
		Administrador adminAtualizado;
		adminAtualizado = generateAdministrador(3, null, "444-444-444-44", null, "Jose@caio", "masculino", 1L, 2,
				"Jose", "Caio");

		Administrador administrador = administradorService.findById(id).get();
		administradorService.updateById(id, adminAtualizado);
		assertEquals(administrador.getNome(), adminAtualizado.getNome());

	}

	/*
	 * Metodo para gerar um administrador recebendo os atributos como parametros de
	 * entrada
	 */

	private Administrador generateAdministrador(int carga, String cell, String cpf, Date data, String email,
			String genero, long id, int nivel, String nome, String sobrenome) {

		Administrador administrador = new Administrador();
		administrador.setCargaHoraria(carga);
		administrador.setCell(cell);
		administrador.setCpf(cpf);
		administrador.setDataNasc(data);
		administrador.setEmail(email);
		administrador.setGenero(genero);
		administrador.setId(id);
		administrador.setNivelDeAcesso(nivel);
		administrador.setNome(nome);
		administrador.setSobreNome(sobrenome);

		return admin;
	}

}
