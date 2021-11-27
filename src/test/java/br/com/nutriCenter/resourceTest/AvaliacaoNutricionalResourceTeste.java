package br.com.nutriCenter.resourceTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nutriCenter.model.AvaliacaoAntropometrica;
import br.com.nutriCenter.model.AvaliacaoGastoEnergetico;
import br.com.nutriCenter.model.AvalicaoDeAnamnese;
import br.com.nutriCenter.services.AvalicaoNutricionalService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AvaliacaoNutricionalResourceTeste {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AvalicaoNutricionalService avalicaoNutricionalService;

	private AvalicaoDeAnamnese anamnese;

	private AvaliacaoAntropometrica antropometrica;

	private AvaliacaoGastoEnergetico gastoEnergetico;

	private final String urlBase = "/api/tratamento";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() throws ParseException {
		objectMapper = new ObjectMapper();

	}

	@Test
	public void deveRetornarSucessoAoCriarUmaAnamneseTeste() throws Exception {
		
		anamnese = generateAnamnese(175, "", "", true, 1L, true, "Anamnese", 80, "nenhuma");
		mockMvc.perform(post(urlBase + "/cadastrarAvalicao/anamnese" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(anamnese))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void deveRetornarSucessoAoCriarUmaAntropometricaTeste() throws Exception{

		antropometrica = generateAntropometrica( 175 , "", true, 0, "Antropometrica", 905 , 78 );
		mockMvc.perform(post(urlBase + "/cadastrarAvalicao/antropometrica" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(antropometrica))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		
	}
	
	@Test
	public void deveRetornarSucessoAoCriarUmaEnergeticoTeste() throws Exception{

		gastoEnergetico = generateEnergetico( 175 , "descricao",1L, "corrida", 75 , "12342020" );
		mockMvc.perform(post(urlBase + "/cadastrarAvalicao/gastoEnergetico" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(gastoEnergetico))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		
	}
	
	
	
	private AvalicaoDeAnamnese generateAnamnese(float altura, String descricao, String alcool, boolean fuma, long id,
			boolean bebida, String nomeAvaliacao, float peso, String restricao) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		AvalicaoDeAnamnese avaliacao = new AvalicaoDeAnamnese();
		avaliacao.setAltura(altura);
		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setFrequenciaQueIngereAlcool(alcool);
		avaliacao.setFumante(fuma);
		avaliacao.setId(id);
		avaliacao.setIngereAlcool(bebida);
		avaliacao.setNomeDaAvaliacao(nomeAvaliacao);
		avaliacao.setPeso(peso);
		avaliacao.setRestricaoAlimentar(restricao);

		return avaliacao;

	}

	private AvaliacaoAntropometrica generateAntropometrica(float altura, String descricao, boolean disponivel, long id,
			String nome, float peso, float pesoIdeal) throws Exception {

		AvaliacaoAntropometrica avaliacao = new AvaliacaoAntropometrica();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		avaliacao.setAltura(altura);
		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setDisponivel(disponivel);
		avaliacao.setId(id);
		avaliacao.setNomeDaAvaliacao(nome);
		avaliacao.setPeso(peso);
		avaliacao.setPesoIdeal(pesoIdeal);

		return avaliacao;
	}

	private AvaliacaoGastoEnergetico generateEnergetico(float altura, String descricao, long id, String atividadeFisica, float peso, String protocolo) throws Exception{
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");
		
		AvaliacaoGastoEnergetico avaliacao = new AvaliacaoGastoEnergetico();
		avaliacao.setAltura(altura);
		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setId(id);
		avaliacao.setNivelDeAtividadeFisica(atividadeFisica);
		avaliacao.setPeso(peso);
		avaliacao.setProtocolo(protocolo);
	
		return avaliacao;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
