package ResourceTest;


import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.resource.NutricionistaResource;
import br.com.nutriCenter.services.NutricionistaService;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;


@RunWith(SpringRunner.class)
public class NutricionistaResourceTest {


	@Autowired
	NutricionistaResource nutricionistaResource;

	@MockBean
	NutricionistaService nutricionistaService;

	@MockBean
	Nutricionista n ;

	@Before
	public void setUp(){
		standaloneSetup(this.nutricionistaResource);
		n = new Nutricionista();
		n.setId(1l);
		n.setNome(null);
		n.setSobreNome(null);
		n.setNivelDeAcesso(2);
		n.setDataNasc(null);
		n.setCpf(null);
		n.setSobreNome(null);
		n.setCRN(null);
		n.setCargaHoraria(3);


	}

	@Test
	public void deveRetornarSucesso_BuscarNutricionista() throws Exception {


		Mockito.when(this.nutricionistaService.findById(1L)).thenReturn(java.util.Optional.of(nutricionistaService.createNutricionista(n)));

		given()
					.accept(ContentType.JSON)
				.when()
					.get("/api/nutricionista/{id}",  1l)
				.then()
					.status(HttpStatus.OK);

	}
	
}
