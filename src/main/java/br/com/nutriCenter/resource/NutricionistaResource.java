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
	/*POST inserindo um objeto no banco de dados*/
	@PostMapping("/nutricionista")
	public void create(@RequestBody Nutricionista nutricionista) {
		this.servico.createNutricionista(nutricionista);
	}
	/*GET listando todos os objetos do banco*/
	@GetMapping("/nutricionistas")
	public List<Nutricionista> findAll(){
		return servico.findAll();
	}
	/*GET buscando um objeto pelo seu identificador*/
	@GetMapping("/nutricionista/{id}")
	public Optional<Nutricionista> findById(@PathVariable(value="id")long id) {
		return this.servico.findById(id);
	}
	/*DELETE removendo um objeto do banco*/
	@DeleteMapping("/nutricionista/{id}")
	public void delete(@PathVariable(value="id")long id) {
		 this.servico.delete(id);
	}
	/*DELETE removendo todos os objetos*/
	@DeleteMapping("/nutricionistasDel")
	public void deleteAll() {
		this.servico.deleteAll();
	}
	/*PUT realizando o update das informações de um objeto*/
	@PutMapping("/nutricionista")
	public Nutricionista update (@RequestBody Nutricionista nutricionista) {
		return this.servico.update(nutricionista);
	}
	
}
