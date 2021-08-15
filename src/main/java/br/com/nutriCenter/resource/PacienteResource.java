package br.com.nutriCenter.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.services.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteResource {

	@Autowired
	private PacienteService servico;

	/**/
	@GetMapping("/paciente/{id}")
	public Optional<Paciente> findById(@PathVariable long id) {
		return servico.findById(id);
	}

	@PostMapping("/paciente")
	public Paciente create(@RequestBody Paciente paciente) {
		return servico.create(paciente);
	}

	@GetMapping("/pacientes")
	public List<Paciente> findAll() {
		return servico.findAll();
	}

	@PutMapping("/paciente")
	public Paciente update(@RequestBody Paciente paciente) {
		return servico.update(paciente);
	}

	@DeleteMapping("/paciente")
	public void delete(@RequestBody Paciente paciente) {
		servico.delete(paciente);
	}

}
