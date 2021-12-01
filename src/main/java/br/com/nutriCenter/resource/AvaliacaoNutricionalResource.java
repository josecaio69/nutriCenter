/**
 * 
 */
package br.com.nutriCenter.resource;

import br.com.nutriCenter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriCenter.exception.ObjectNotFoundException;
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

	@PostMapping("/anamnese/{idPaciente}")
	public ResponseEntity<AvalicaoDeAnamnese> cadastrarAvalicaoAnamnese(@PathVariable(value = "idPaciente") long idPaciente,
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
	
	@PostMapping("/antropometrica/{idPaciente}")
	public ResponseEntity<AvaliacaoAntropometrica> cadastrarAvalicaoAntropometrica(@PathVariable(value = "idPaciente") long idPaciente,
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
	
	@PostMapping("/gastoEnergetico/{idPaciente}")
	public ResponseEntity<AvaliacaoGastoEnergetico> cadastrarAvalicaoGastoEnergetico(@PathVariable(value = "idPaciente") long idPaciente,
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

	@PostMapping("/suplementacao/{idPaciente}")
	public ResponseEntity<AvaliacaoDeSuplementacao> cadastrarAvalicaoSuplementacao(@PathVariable(value = "idPaciente") long idPaciente,
																				 @RequestBody AvaliacaoDeSuplementacao avalicao) {
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
