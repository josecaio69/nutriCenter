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

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.services.NutricionistaService;

@RestController()
@RequestMapping(value = "/api")
public class NutricionistaResource {
	
	@Autowired
	private NutricionistaService servico;
	
	@PostMapping("/nutricionista")
	public void create(@RequestBody Nutricionista nutricionista) {
		this.servico.createNutricionista(nutricionista);
	}
	
	@GetMapping("/nutricionistas")
	public List<Nutricionista> findAll(){
		return servico.findAll();
	}
	
	@GetMapping("/nutricionista/{id}")
	public Optional<Nutricionista> findById(@PathVariable(value="id")long id) {
		return this.servico.findById(id);
	}
	
	@DeleteMapping("/nutricionista")
	public void delete(@RequestBody Nutricionista nutricionista) {
		 this.servico.delete(nutricionista);
	}
	
	@PutMapping("/nutricionista")
	public Nutricionista update (@RequestBody Nutricionista nutricionista) {
		return this.servico.update(nutricionista);
	}
	
}
