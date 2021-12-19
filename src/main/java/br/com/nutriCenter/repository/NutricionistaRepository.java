package br.com.nutriCenter.repository;

import java.util.List;
import java.util.Optional;

import br.com.nutriCenter.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Nutricionista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author José Caio
 *
 */
@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long>{
	
	List<Nutricionista> findByNomeContaining(String nome);

	Optional<Nutricionista> findByEmail(String email);

	@Query("select n from Nutricionista n where n.email = :email and n.senha = :senha")
	Optional<Nutricionista> findByEmailAndPassword(String email, String senha);



}
