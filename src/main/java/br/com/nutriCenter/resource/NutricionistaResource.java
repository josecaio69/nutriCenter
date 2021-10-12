package br.com.nutriCenter.resource;

import java.util.List;

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
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.services.NutricionistaService;

@RestController()
@RequestMapping(value = "/api")
public class NutricionistaResource {

	@Autowired
	private NutricionistaService servico;

	/* POST inserindo um objeto no banco de dados */
	@PostMapping("/nutricionista")
	public ResponseEntity<Nutricionista> create(@RequestBody Nutricionista nutricionista) {
		try {
			Nutricionista profissionalDeNutricao = this.servico.createNutricionista(nutricionista);
			return new ResponseEntity<>(profissionalDeNutricao, HttpStatus.CREATED);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* GET listando todos os objetos do banco */
	@GetMapping("/nutricionistas")
	public ResponseEntity<List<Nutricionista>> findAll() {
		try {
			return new ResponseEntity<>(this.servico.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* GET buscando um objeto pelo seu identificador */
	@GetMapping("/nutricionista/{id}")
	public ResponseEntity<Nutricionista> findById(@PathVariable(value = "id") long id) {
		try {
			Nutricionista profissionalDeNutricao = this.servico.findById(id).get();
			return new ResponseEntity<>(profissionalDeNutricao, HttpStatus.OK);
		} catch (ObjectNotFoundException erro) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* DELETE removendo um objeto do banco */
	@DeleteMapping("/nutricionista")
	public ResponseEntity<Nutricionista> delete(@RequestBody Nutricionista nutricionista) {
		try {
			this.servico.delete(nutricionista);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* DELETE removendo todos os objetos */
	@DeleteMapping("/nutricionistasDel")
	public ResponseEntity<?> deleteAll() {
		try {
			this.servico.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* PUT realizando o update das informações de um objeto */
	@PutMapping("/nutricionista")
	public ResponseEntity<Nutricionista> update(@RequestBody Nutricionista nutricionista) {
		try {
			this.servico.update(nutricionista);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* PUT update pelo ID do objeto */
	@PutMapping("/nutricionista/{id}")
	public ResponseEntity<Nutricionista> updatePeloId(@PathVariable(value = "id") long id,
			@RequestBody Nutricionista nutricionista) {
		try {
			this.servico.updateById(id, nutricionista);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* GET buscar por nome ou parte do nome do profissional de nutricao */
	@GetMapping("/byName/{nome}")
	public ResponseEntity<List<Nutricionista>> findAllByName(@PathVariable(value = "nome") String nome) {
		try {
			List<Nutricionista> list = this.servico.findByName(nome);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
