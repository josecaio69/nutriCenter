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
import br.com.nutriCenter.resource.AvaliacaoNutricionalResource;
import br.com.nutriCenter.services.AvalicaoNutricionalService;

/**
 * @author Mateus Fernandes
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AvaliacaoNutricionalResourceTeste {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AvaliacaoNutricionalResource avaliacaoControlle;

	@MockBean
	private AvalicaoNutricionalService avalicaoNutricionalService;

	private AvalicaoDeAnamnese anamnese1;

	private AvaliacaoAntropometrica antropometrica1;

	private AvaliacaoGastoEnergetico gastoEnergetico1;

	private final String urlBase = "/api/tratamento";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		anamnese1 = generateAnamnese("Avaliacao de anamnese", 1L, "Anamnese", "anamnese");

		antropometrica1 = generateAntropometrica(1.75f, "Descrição", true, 1L, "Antropometrica", 80, 70, 24, 23, 44, 45,
				23, 45, 56, 23, 57, 43, 23, 34, 78, 34, 67, 67, 45, 76, 34, "Avaliacao antropometrica");

		gastoEnergetico1 = generateEnergetico(175, "descricao", 1L, "corrida", 75, "12342020",
				"Avaliacao de gasto energetico", "Gasto Energetico");

	}

	@Test
	public void deveRetornarSucessoAoCriarUmaAnamneseTeste() throws Exception {

		mockMvc.perform(
				post(urlBase + "/anamnese" + "/{idPaciente}", 1L).content(objectMapper.writeValueAsString(anamnese1))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deveRetornarSucessoAoCriarUmaAntropometricaTeste() throws Exception {
		mockMvc.perform(post(urlBase + "/antropometrica" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(antropometrica1))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void deveRetornarSucessoAoCriarUmaEnergeticoTeste() throws Exception {

		mockMvc.perform(post(urlBase + "/gastoEnergetico" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(gastoEnergetico1))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void deveRetornarErroAoTentarCadastrarAnamneseSemTituloTeste() throws Exception {

		AvalicaoDeAnamnese anamnese;
		anamnese = generateAnamnese("Descricao", 1L, "Avaliacao de anamnese", "");
		mockMvc.perform(
				post(urlBase + "/anamnese" + "/{idPaciente}", 1L).content(objectMapper.writeValueAsString(anamnese))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void deveRetornarErroAoTentarCadastrarAnamneseSemTipoTeste() throws Exception {

		AvalicaoDeAnamnese anamnese;
		anamnese = generateAnamnese("Descricao", 1L, "", "anamnese");
		mockMvc.perform(
				post(urlBase + "/anamnese" + "/{idPaciente}", 1L).content(objectMapper.writeValueAsString(anamnese))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	

	@Test
	public void deveRetornarErroAoTentarCadastrarAntropometricaSemTituloTeste() throws Exception {
		
		AvaliacaoAntropometrica antropometrica;
		antropometrica =  generateAntropometrica(1.75f, "Descrição", true, 1L, "Antropometrica", 80, 70, 24, 23, 44, 45,
				23, 45, 56, 23, 57, 43, 23, 34, 78, 34, 67, 67, 45, 76, 34, "");
		mockMvc.perform(post(urlBase + "/antropometrica" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(antropometrica))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	public void deveRetornarErroAoTentarCadastrarAntropometricaSemTipoTeste() throws Exception {
		
		AvaliacaoAntropometrica antropometrica;
		antropometrica =  generateAntropometrica(1.75f, "Descrição", true, 1L, "", 80, 70, 24, 23, 44, 45,
				23, 45, 56, 23, 57, 43, 23, 34, 78, 34, 67, 67, 45, 76, 34, "Avaliação Antropometrica");
		mockMvc.perform(post(urlBase + "/antropometrica" + "/{idPaciente}", 1L)
				.content(objectMapper.writeValueAsString(antropometrica))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		
	}
	
	
	
	
	
	private AvalicaoDeAnamnese generateAnamnese(String descricao, long id, String nomeAvaliacao, String titulo)
			throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		AvalicaoDeAnamnese avaliacao = new AvalicaoDeAnamnese();
		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setId(id);
		avaliacao.setTipo(nomeAvaliacao);
		avaliacao.setTitulo(titulo);
		return avaliacao;

	}

	private AvaliacaoAntropometrica generateAntropometrica(float altura, String descricao, boolean disponivel, long id,
			String tipo, float peso, float abdomen, float antDireito, float antEsquerdo, float bDContraido,
			float bDRelaxado, float bEContraido, float bERelaxado, float cintura, float coxaEsquerda,
			float coxaProxDireita, float coxDireita, float coxaProxEsquerda, float ombro, float pantDireita,
			float pantEsquerda, float peitoral, float pescoco, float punhoDirei, float punhoEsquer, float quadril,
			String titulo) throws Exception {

		AvaliacaoAntropometrica avaliacao = new AvaliacaoAntropometrica();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setDisponivel(disponivel);
		avaliacao.setId(id);
		avaliacao.setTipo(tipo);
		avaliacao.setAltura(altura);
		avaliacao.setPeso(peso);
		avaliacao.setAbdomen(abdomen);
		avaliacao.setAntebracoDireito(antDireito);
		avaliacao.setAntebracoEsquerdo(antEsquerdo);
		avaliacao.setBracoDireitoContraido(bDContraido);
		avaliacao.setBracoDireitoRelaxado(bDRelaxado);
		avaliacao.setBracoEsquerdoContraido(bEContraido);
		avaliacao.setBracoEsquerdoRelaxado(bERelaxado);
		avaliacao.setCintura(cintura);
		avaliacao.setCoxaDireita(coxDireita);
		avaliacao.setCoxaEsquerda(coxaEsquerda);
		avaliacao.setCoxaProximalDireita(coxaProxDireita);
		avaliacao.setCoxaProximalEsquerda(coxaProxEsquerda);
		avaliacao.setOmbro(ombro);
		avaliacao.setPanturrilhaDireita(pantDireita);
		avaliacao.setPanturrilhaEsquerda(pantEsquerda);
		avaliacao.setPeitoral(peitoral);
		avaliacao.setPescoco(pescoco);
		avaliacao.setPunhoDireito(punhoDirei);
		avaliacao.setPunhoEsquerdo(punhoEsquer);
		avaliacao.setQuadril(quadril);
		avaliacao.setTitulo(titulo);
		return avaliacao;
	}

	private AvaliacaoGastoEnergetico generateEnergetico(float altura, String descricao, long id, String atividadeFisica,
			float peso, String protocolo, String tipo, String titulo) throws Exception {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");

		AvaliacaoGastoEnergetico avaliacao = new AvaliacaoGastoEnergetico();
		avaliacao.setData(data);
		avaliacao.setDescricao(descricao);
		avaliacao.setId(id);
		avaliacao.setNivelDeAtividadeFisica(atividadeFisica);
		avaliacao.setProtocolo(protocolo);
		avaliacao.setPeso(peso);
		avaliacao.setAltura(altura);
		avaliacao.setTipo(tipo);
		avaliacao.setTitulo(titulo);
		return avaliacao;

	}

}
