package br.com.nutriCenter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Paciente;
import org.springframework.stereotype.Repository;

/**
 * @author José Caio
 *
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	Optional<Paciente> findByCpf(String cpf);

	Optional<Paciente> findByNome(String nome);
}
