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

import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.services.AdministradorService;

@RestController
@RequestMapping(value = "/api")
public class AdministradorResource {

	@Autowired
	private AdministradorService servico;

	@PostMapping("/admin")
	public void create(@RequestBody Administrador adm) {
		this.servico.create(adm);
	}

	@GetMapping("/admins")
	public List<Administrador> findAll() {
		return servico.findAll();
	}

	@GetMapping("/admin/{id}")
	public Optional<Administrador> findById(@PathVariable(value = "id") long id) {
		return this.servico.findById(id);
	}

	@DeleteMapping("/admin")
	public void delete(@RequestBody Administrador adm) {
		this.servico.delete(adm);
	}

	@PutMapping("/admin")
	public Administrador update(@RequestBody Administrador adm) {
		return this.servico.update(adm);
	}

}
