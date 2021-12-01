package br.com.nutriCenter.serviceTest;

import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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

import br.com.nutriCenter.model.Consulta;
import br.com.nutriCenter.repository.ConsultaRepository;
import br.com.nutriCenter.services.ConsultaService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class ConsultaServiceTeste {

	
	@TestConfiguration
	static class ConsultaServiceTestConfiguration{
		
		@Bean
		public ConsultaService consultaService() {
			return new ConsultaService();
		};
	}
	
	
	@Autowired
	ConsultaService consultaService;
	
	@MockBean
	ConsultaRepository consultaRepository;
	
	Consulta consulta;
	
	
	
	@BeforeEach
	public void setUp() throws Exception{
		

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");
		
		consulta = new Consulta();
		consulta.setDataDaConsulta(data);
		consulta.setHoraDaConsulta(null);
		consulta.setId(1L);
		consulta.setPaciente(null);
		consulta.setValor(null);
		
		
		consultaService.createConsulta(consulta, 1L, 1L);
		
		Mockito.when(consultaRepository.findById(consulta.getId())).thenReturn(Optional.ofNullable(consulta));
		
		
	}
	
	
	@Test
	public void buscarConsultaPeloIdTeste() throws Exception{
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
