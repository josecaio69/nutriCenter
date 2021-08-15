package br.com.nutriCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
}
