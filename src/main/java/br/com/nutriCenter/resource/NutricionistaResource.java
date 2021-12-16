package br.com.nutriCenter.resource;

import java.util.List;
import javax.validation.Valid;
import br.com.nutriCenter.model.Consulta;
import br.com.nutriCenter.services.ConsultaService;
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

/**
 * @author José Caio
 *
 */
@RestController()
@RequestMapping(value = "/api/nutricionista")
public class NutricionistaResource {

	@Autowired
	private NutricionistaService servico;

	@Autowired
	private ConsultaService consultaService;

	/* POST inserindo um objeto no banco de dados */
	@PostMapping()
	public ResponseEntity<Nutricionista> create(@RequestBody @Valid Nutricionista nutricionista) {
		try {
			Nutricionista profissionalDeNutricao = this.servico.createNutricionista(nutricionista);
			return new ResponseEntity<>(profissionalDeNutricao, HttpStatus.CREATED);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* GET listando todos os objetos do banco */
	@GetMapping("/getAll")
	public ResponseEntity<List<Nutricionista>> findAll() {
		try {
			return new ResponseEntity<>(this.servico.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* GET buscando um objeto pelo seu identificador */
	@GetMapping("/{id}")
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

	/* DELETE removendo um objeto do banco pelo ID */
	@DeleteMapping("/{id}")
	public ResponseEntity<Nutricionista> deleteById(@PathVariable(value = "id") long id) {
		try {
			this.servico.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* DELETE removendo um objeto do banco */
	@DeleteMapping()
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
	@DeleteMapping("/delAll")
	public ResponseEntity<?> deleteAll() {
		try {
			this.servico.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* PUT realizando o update das informações de um objeto */
	@PutMapping()
	public ResponseEntity<Nutricionista> update(@RequestBody @Valid Nutricionista nutricionista) {
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
	@PutMapping("/{id}")
	public ResponseEntity<Nutricionista> updatePeloId(@PathVariable(value = "id") long id,
			@RequestBody @Valid Nutricionista nutricionista) {
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

	@GetMapping("/getAllConsultas")
	public List<Consulta> getConsultas() {
		return this.consultaService.findAll();
	}

	@PostMapping("/createConsulta/{id}/{id}")
	public Consulta createConsulta(@RequestBody Consulta consulta) {
		return null;
	}

}
