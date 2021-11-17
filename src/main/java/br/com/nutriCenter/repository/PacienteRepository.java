package br.com.nutriCenter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Paciente;

/**
 * @author José Caio
 *
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	Optional<Paciente> findByCpf(String cpf);
}
