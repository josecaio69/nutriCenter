/**
 * 
 */
package br.com.nutriCenter.resource;

import br.com.nutriCenter.model.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private AvalicaoNutricionalService servicoDeAvaliacao;

	/*
	 * 
	 * Estes dois primeiros metodos de, GET e DELETE (uma avaliação) não precisam
	 * ser diferenciados por tipo de avaliação AQUI NO RESOURCE, ou seja, só precisa
	 * de um metodo para todos os tipos de avaliação
	 * 
	 */

	@DeleteMapping("/{idPaciente}/{idAvaliacao}")
	public ResponseEntity<AvaliacaoNutricional> deleteAvalicao(@PathVariable(value = "idPaciente") long idPaciente,
			@PathVariable(value = "idAvaliacao") long idAvaliacao) {
		try {
			this.servicoDeAvaliacao.deleteAvaliacao(idPaciente, idAvaliacao);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get 1 só precisa de 1 metodo
	@GetMapping("/{idPaciente}/{idAvaliacao}")
	public ResponseEntity<AvaliacaoNutricional> getAvalicao(@PathVariable(value = "idPaciente") long idPaciente,
			@PathVariable(value = "idAvaliacao") long idAvaliacao) {
		try {
			AvaliacaoNutricional a = this.servicoDeAvaliacao.getAvaliacao(idPaciente, idAvaliacao);
			return new ResponseEntity<AvaliacaoNutricional>(a, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{idPaciente}")
	public ResponseEntity<List<AvaliacaoNutricional>> listarAvaliacoes(
			@PathVariable(value = "idPaciente") long idPaciente) {
		try {
			List<AvaliacaoNutricional> a = this.servicoDeAvaliacao.listarAvaliacoes(idPaciente);
			return new ResponseEntity<List<AvaliacaoNutricional>>(a, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Para os metodos: cadastrar, atualizar e listar, é preciso ter um método
	 * diferente para cada tipo de avaliação AQUI NO RESOURCE. Estes ficarão aqui
	 * abaixo pois, acima estão os metodos que só precisam de um para todos os tipos
	 * de avaliação.
	 * 
	 */

	/*
	 * ===========================================================================*
	 * METODOS DE ANAMNESE *
	 * ==========================================================================
	 */

	@PostMapping("/anamnese/{idPaciente}")
	public ResponseEntity<AvaliacaoDeAnamnese> cadastrarAvalicaoAnamnese(
			@PathVariable(value = "idPaciente") long idPaciente, @RequestBody @Valid AvaliacaoDeAnamnese avaliacao) {
		try {
			this.servicoDeAvaliacao.adicionarAvaliacao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/anamnese/{idPaciente}")
	public ResponseEntity<AvaliacaoDeAnamnese> atualizarAvalicaoAnamnese(
			@PathVariable(value = "idPaciente") long idPaciente, @RequestBody AvaliacaoDeAnamnese avaliacao) {
		try {
			this.servicoDeAvaliacao.atualizarAvalicao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/anamnese/{idPaciente}")
	public ResponseEntity<List<AvaliacaoDeAnamnese>> listarAvalicaoAnamnese(
			@PathVariable(value = "idPaciente") long idPaciente) {
		try {
			List<AvaliacaoDeAnamnese> a = this.servicoDeAvaliacao.listarAnamnese(idPaciente);
			return new ResponseEntity<List<AvaliacaoDeAnamnese>>(a, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * ===========================================================================*
	 * METODOS DE ANTROPOMETRICA *
	 * ==========================================================================
	 */

	@PostMapping("/antropometrica/{idPaciente}")
	public ResponseEntity<AvaliacaoAntropometrica> cadastrarAvalicaoAntropometrica(
			@PathVariable(value = "idPaciente") long idPaciente, @RequestBody @Valid AvaliacaoAntropometrica avalicao) {
		try {
			this.servicoDeAvaliacao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/antropometrica/{idPaciente}")
	public ResponseEntity<AvaliacaoAntropometrica> atualizarAvalicaoAntrpometrica(
			@PathVariable(value = "idPaciente") long idPaciente, @RequestBody AvaliacaoAntropometrica avaliacao) {
		try {
			this.servicoDeAvaliacao.atualizarAvalicao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("antropometrica/{idPaciente}")
	public ResponseEntity<List<AvaliacaoAntropometrica>> listarAvalicaoAntropometrica(
			@PathVariable(value = "idPaciente") long idPaciente) {
		try {
			List<AvaliacaoAntropometrica> a = this.servicoDeAvaliacao.listarAntropometrica(idPaciente);
			return new ResponseEntity<List<AvaliacaoAntropometrica>>(a, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * ==========================================================================
	 * METODOS DE EXAMES 
	 * ==========================================================================
	 */

	@PostMapping("/exame/{idPaciente}")
	public ResponseEntity<Exame> cadastrarExame(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody @Valid Exame avalicao) {
		try {
			this.servicoDeAvaliacao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/exame/{idPaciente}")
	public ResponseEntity<Exame> atualizarExame(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody Exame avaliacao) {
		try {
			this.servicoDeAvaliacao.atualizarAvalicao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * ==========================================================================
	 * METODOS DE GASTO ENERGETICO 
	 * ==========================================================================
	 */

	@PostMapping("/gastoEnergetico/{idPaciente}")
	public ResponseEntity<AvaliacaoGastoEnergetico> cadastrarAvalicaoGastoEnergetico(
			@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody @Valid AvaliacaoGastoEnergetico avalicao) {
		try {
			this.servicoDeAvaliacao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/gastoEnergetico/{idPaciente}")
	public ResponseEntity<AvaliacaoGastoEnergetico> atualizarAvaliacaoGastoEnergetico(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody AvaliacaoGastoEnergetico avaliacao) {
		try {
			this.servicoDeAvaliacao.atualizarAvalicao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * ===========================================================================*
	 * METODOS DE SUPLEMENTAÇÃO *
	 * ==========================================================================
	 */

	@PostMapping("/suplementacao/{idPaciente}")
	public ResponseEntity<AvaliacaoDeSuplementacao> cadastrarAvalicaoSuplementacao(
			@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody @Valid AvaliacaoDeSuplementacao avalicao) {
		try {
			this.servicoDeAvaliacao.adicionarAvaliacao(idPaciente, avalicao);
			return new ResponseEntity<>(avalicao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/suplementacao/{idPaciente}")
	public ResponseEntity<AvaliacaoDeSuplementacao> atualizarAvaliacaoSuplementacao(@PathVariable(value = "idPaciente") long idPaciente,
			@RequestBody AvaliacaoDeSuplementacao avaliacao) {
		try {
			this.servicoDeAvaliacao.atualizarAvalicao(idPaciente, avaliacao);
			return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
