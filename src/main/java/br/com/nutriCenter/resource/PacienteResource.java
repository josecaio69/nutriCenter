package br.com.nutriCenter.resource;

import java.util.List;

import javax.validation.Valid;

import br.com.nutriCenter.services.NutricionistaService;
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
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.services.PacienteService;

/**
 * @author José Caio
 *
 */

@RestController
@RequestMapping("/api/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService servico;


	/* GET paciente by Id */
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable long id) {
		try {
			Paciente patient = this.servico.findById(id).get();
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Paciente>> findAll() {
		try {
			return new ResponseEntity<>(this.servico.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Paciente> create(@RequestBody @Valid Paciente paciente) {
		try {
			Paciente patient = this.servico.create(paciente);
			return new ResponseEntity<>(patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addPaciente/{idDoNutricionista}")
	public ResponseEntity<Paciente> createFromNutricionista(@PathVariable(value = "idDoNutricionista") long idDoNutricionista, @RequestBody @Valid Paciente paciente) {
		try {
			System.out.println("AQUIAUIQUAUIQUIQ");
			Paciente patient = this.servico.createNewPaciente(idDoNutricionista, paciente);
			return new ResponseEntity<>(patient, HttpStatus.CREATED);
		}catch (Exception erro){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping()
	public ResponseEntity<Paciente> update(@RequestBody @Valid Paciente paciente) {
		try {
			Paciente patient = this.servico.update(paciente);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Paciente> updateById(@PathVariable(value = "id") long id, @RequestBody @Valid Paciente paciente) {
		try {
			Paciente patient = this.servico.updateById(id, paciente);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping()
	public ResponseEntity<Paciente> delete(@RequestBody Paciente paciente) {
		try {
			this.servico.delete(paciente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Paciente> deleteById(@PathVariable(value = "id") long id) {
		try {
			this.servico.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delAll")
	public ResponseEntity<Paciente> deleteAll() {
		try {
			this.servico.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
