package br.com.nutriCenter.repository;

import br.com.nutriCenter.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Administrador;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author José Caio
 *
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Long>{

    Optional<Administrador> findByEmail(String email);
}
