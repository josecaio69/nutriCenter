package br.com.nutriCenter.serviceTest;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.repository.NutricionistaRepository;
import br.com.nutriCenter.services.NutricionistaService;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NutricionistaServiceTest {

	@TestConfiguration
	static class NutricionistaServiceTestConfiguration{

		@Bean
		public NutricionistaService nutricionistaService(){
		return new NutricionistaService();

		};

	}
	@Autowired
	 NutricionistaService nutricionistaService;

	@MockBean
	NutricionistaRepository nutricionistaRepository;
	


	Nutricionista nutri ;
	
	@Before
	public void setUp() {

		nutri = new Nutricionista();
		nutri.setNome("Mateus");
		nutri.setSobreNome("Fernandes");
		nutri.setCRN("12345");
		nutri.setCargaHoraria(10);
		nutri.setNivelDeAcesso(2);
		nutri.setCell("83 999475005");
		nutri.setCpf("111.111.111-11");
		nutri.setDataNasc(null);
		nutri.setEmail("mateus@mateus");
		nutri.setId(1L);
		nutricionistaService.createNutricionista(nutri);

		Mockito.when(nutricionistaRepository.findById(nutri.getId()))
				.thenReturn(Optional.ofNullable(nutri));
	}

	@Test
	public void buscarNutricionistaPeloId()throws Exception {

		Nutricionista profissionalNutricao = nutricionistaService.findById(nutri.getId()).get();
		Assertions.assertEquals("12345", profissionalNutricao.getCRN());

	}


	@Test
	public void deletarNutricionistaPeloId() throws Exception {
		
		nutricionistaService.delete(nutri);
		Optional<Nutricionista> profissionalDeNutricao = nutricionistaService.findById(nutri.getId());
		assertFalse(profissionalDeNutricao.isPresent());
	}


};
