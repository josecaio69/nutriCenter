package br.com.nutriCenter.resourceTest;

import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.resource.AdministradorResource;
import br.com.nutriCenter.resource.NutricionistaResource;
import br.com.nutriCenter.services.AdministradorService;
import br.com.nutriCenter.services.NutricionistaService;
import groovyjarjarpicocli.CommandLine.ExecutionException;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

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
	public void setUp() {
		objectMapper = new ObjectMapper();
		administrador = new Administrador();
		
		administrador.setId(1L);
		administrador.setNome("MAteus");
		administrador.setSobreNome("Fernandes");
		administrador.setEmail("mateus@email.com");
		administrador.setGenero("Masculino");
		administrador.setCpf("222-222-222-22");
		
	}
	
	
	/* Test do GetAll de Administradores */

	@Test
	public void deveRetornarSucessoAoListarTodosOsAdministradores() throws Exception{
		mockMvc.perform(get(urlBase + "/getAll").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	/* Test Post inserir um novo Administrador */

	@Test
	public void deveRetornarSucessoAoTentarAdicionarUmNovoAdministradorTest() throws Exception {
		mockMvc.perform(post(urlBase).content(objectMapper.writeValueAsString(administrador))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
