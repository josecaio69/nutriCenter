package br.com.nutriCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Nutricionista;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long>{
	
	List<Nutricionista> findByNomeContaining(String nome);

}
