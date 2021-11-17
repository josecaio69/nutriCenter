/**
 * 
 */
package br.com.nutriCenter.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.AvaliacaoAntropometrica;
import br.com.nutriCenter.model.AvaliacaoGastoEnergetico;
import br.com.nutriCenter.model.AvaliacaoNutricional;
import br.com.nutriCenter.model.AvalicaoDeAnamnese;
import br.com.nutriCenter.services.AvalicaoNutricionalService;

/**
 * @author José Caio
 *
 */
@RestController
@RequestMapping(value = "/api/tratamento")
public class AvaliacaoNutricionalResource {
	
	@Autowired
	private AvalicaoNutricionalService servicoDeAvalicao;

	@PostMapping("/cadastrarAvalicao/anamnese/{idPaciente}")
	public ResponseEntity<AvaliacaoNutricional> cadastrarAvalicaoAnamnese(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody AvalicaoDeAnamnese avalicao) {
		try {
			this.servicoDeAvalicao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/cadastrarAvalicao/antropometrica/{idPaciente}")
	public ResponseEntity<AvaliacaoNutricional> cadastrarAvalicaoAntropometrica(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody AvaliacaoAntropometrica avalicao) {
		try {
			this.servicoDeAvalicao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/cadastrarAvalicao/gastoEnergetico/{idPaciente}")
	public ResponseEntity<AvaliacaoNutricional> cadastrarAvalicaoGastoEnergetico(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody AvaliacaoGastoEnergetico avalicao) {
		try {
			this.servicoDeAvalicao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
