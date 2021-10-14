package br.com.nutriCenter.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.repository.NutricionistaRepository;
@Service
public class NutricionistaService {
	
	@Autowired
	private NutricionistaRepository repositorio;
	
	public Nutricionista createNutricionista(Nutricionista entidade) {
		return repositorio.save(entidade);
	}
	
	public List<Nutricionista> findAll(){
		return this.repositorio.findAll();
	}
	
	public Optional<Nutricionista> findById(Long id) {
		return this.repositorio.findById(id);
	}
	
	public void delete(Long id) {
		this.repositorio.deleteById(id);
	}
	public void deleteAll() {
		this.repositorio.deleteAll();
	}
	
	public Nutricionista update(Nutricionista nutricionista) {
		return this.repositorio.save(nutricionista);
	}
	

}
