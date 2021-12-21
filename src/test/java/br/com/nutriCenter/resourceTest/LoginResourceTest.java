package br.com.nutriCenter.resourceTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceSchemaCreatedEvent;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.resource.LoginResource;
import br.com.nutriCenter.services.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginResource loginController;

	@MockBean
	private LoginService loginService;
	
	private Login login;

	private final String urlBase = "/api/login";

	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	public void setUp() {
		
	}
	
	
	@Test
	public void deveRetornarSucessoAoLogarTeste() throws Exception{
		mockMvc.perform(get(urlBase).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	
	
	
	
	
	


}
