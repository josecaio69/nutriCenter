package br.com.nutriCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nutriCenter.model.Administrador;
import org.springframework.stereotype.Repository;

/**
 * @author José Caio
 *
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Long>{

}
