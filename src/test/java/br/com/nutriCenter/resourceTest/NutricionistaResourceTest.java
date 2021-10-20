package br.com.nutriCenter.resourceTest;

import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.resource.NutricionistaResource;
import br.com.nutriCenter.services.NutricionistaService;

/**
 * @author José Caio
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
	public void setUp() {
		objectMapper = new ObjectMapper();
		nutricionista = new Nutricionista();

		nutricionista.setId(1L);
		nutricionista.setNome("Caio");
		nutricionista.setSobreNome("Araujo");
		nutricionista.setEmail("jose@email.com.br");
		nutricionista.setGenero("Masculino");
		nutricionista.setCpf("111-111-111-11");

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

		when(this.nutricionistaService.findById(1L)).thenReturn(
				generateNutricionista(1L, "mateus", "fernandes", "mateus@email.com", "masculino", "222-222-222-22"));

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

		when(this.nutricionistaService.findById(1L)).thenReturn(
				generateNutricionista(1L, "mateus", "fernandes", "mateus@email.com", "masculino", "222-222-222-22"));

		mockMvc.perform(get(urlBase + "/byName/{nome}", "mateus").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	
	private Optional<Nutricionista> generateNutricionista(long id, String nome, String sobrneome, String email,
			String genero, String cpf) {

		Nutricionista nutri = new Nutricionista();
		nutri.setId(id);
		nutri.setNome(nome);
		nutri.setSobreNome(sobrneome);
		nutri.setEmail(email);
		nutri.setGenero(genero);
		nutri.setCpf(cpf);

		return Optional.ofNullable(nutri);

	}

}
