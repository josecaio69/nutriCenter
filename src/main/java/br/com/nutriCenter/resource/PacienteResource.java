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
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.services.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteResource {

	@Autowired
	private PacienteService servico;

	/* GET paciente by Id */
	@GetMapping("/paciente/{id}")
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

	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> findAll() {
		try {
			return new ResponseEntity<>(this.servico.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/paciente")
	public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
		try {
			Paciente patient = this.servico.create(paciente);
			return new ResponseEntity<>(patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/paciente")
	public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
		try {
			Paciente patient = this.servico.update(paciente);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/paciente")
	public ResponseEntity<Paciente> delete(@RequestBody Paciente paciente) {
		try {
			this.servico.delete(paciente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
