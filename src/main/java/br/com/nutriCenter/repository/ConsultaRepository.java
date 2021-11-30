package br.com.nutriCenter.repository;

import br.com.nutriCenter.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
