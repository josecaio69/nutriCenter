package br.com.nutriCenter.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.repository.NutricionistaRepository;
import br.com.nutriCenter.services.NutricionistaService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class NutricionistaServiceTest {

	@TestConfiguration
	static class NutricionistaServiceTestConfiguration {

		@Bean
		public NutricionistaService nutricionistaService() {
			return new NutricionistaService();

		};

	}

	@Autowired
	NutricionistaService nutricionistaService;

	@MockBean
	NutricionistaRepository nutricionistaRepository;

	Nutricionista nutri;

	@BeforeEach
	public void setUp() {

		nutri = new Nutricionista();
		nutri.setNome("Mateus");
		nutri.setSobreNome("Fernandes");
		nutri.setCRN("12345");
		nutri.setCargaHoraria(10);
		nutri.setNivelDeAcesso(2);
		nutri.setCell("83 999475005");
		nutri.setDataNasc(null);
		nutri.setEmail("mateus@mateus");
		nutri.setId(1L);
		nutricionistaService.createNutricionista(nutri);

		Mockito.when(nutricionistaRepository.findById(nutri.getId())).thenReturn(Optional.ofNullable(nutri));
	}

	/* Teste do Buscar Nutricionista Pelo ID */

	@Test
	public void buscarNutricionistaPeloIdTeste() throws Exception {

		long id = 1L;

		Nutricionista profissionalNutricao = nutricionistaService.findById(id).get();
		Assertions.assertEquals(profissionalNutricao.getId(), id);

	}

	/* Teste do Deletar Nutricionista Pelo ID 
	 * Está com erro*/

	@Test
	public void deletarNutricionistaPeloIdTeste() throws Exception {

		Optional<Nutricionista> profissionalDeNutricao = nutricionistaService.findById(nutri.getId());
		nutricionistaService.deleteById(profissionalDeNutricao.get().getId());
		assertFalse(!profissionalDeNutricao.isPresent());
	}

	/* Teste do Deletar objeto Nutricionista */

	@Test
	public void deletarObjetoNutricionistaTeste() throws Exception {

		Optional<Nutricionista> profissionalDeNutricao = nutricionistaService.findById(nutri.getId());
		nutricionistaService.delete(profissionalDeNutricao.get());
		var listNutri = nutricionistaService.findAll();
		assertEquals(0, listNutri.size());

	}
	

	/* Teste do Atualizar Nutricionista Pelo ID */

	@Test
	public void updatePeloIdNutricionistaPeloIdTeste() throws Exception {

		long id = 1L;
		Nutricionista nutriAtualizado;
		nutriAtualizado = generateNutricionista(1L, "Jose", "Caio", "Jose@caio", "masculino", "444-444-444-44");

		Nutricionista profissionalNutricao = nutricionistaService.findById(id).get();
		nutricionistaService.updateById(id, nutriAtualizado);
		assertEquals(profissionalNutricao.getNome(), nutriAtualizado.getNome());

	}

	/*
	 * Metodo para gerar um nuticionista recebendo os atributos como parametros de
	 * entrada
	 */

	private Nutricionista generateNutricionista(long id, String nome, String sobrneome, String email, String genero,
			String cpf) {

		Nutricionista nutri = new Nutricionista();
		nutri.setId(id);
		nutri.setNome(nome);
		nutri.setSobreNome(sobrneome);
		nutri.setEmail(email);
		nutri.setGenero(genero);

		return nutri;

	}

};
