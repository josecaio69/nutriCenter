package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository repositorio;

	public void create(Administrador adm) {
		repositorio.save(adm);
	}

	public List<Administrador> findAll() {
		return repositorio.findAll();
	}

	public Optional<Administrador> findById(long id) {
		return repositorio.findById(id);
	}

	public void delete(Administrador adm) {
		repositorio.delete(adm);
	}

	public Administrador update(Administrador adm) {
		return repositorio.save(adm);
	}

}
