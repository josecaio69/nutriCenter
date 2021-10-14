package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repositorio;

	public Optional<Paciente> findById(long id) throws Exception {
		if (!this.repositorio.findById(id).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			return this.repositorio.findById(id);
		}
	}

	public List<Paciente> findAll() throws Exception {
		return repositorio.findAll();
	}

	public Paciente update(Paciente paciente) throws Exception {
		if (!this.findById(paciente.getId()).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			return repositorio.save(paciente);
		}
	}

	public Paciente create(Paciente paciente) throws Exception {
		return repositorio.save(paciente);
	}

	public void delete(Paciente paciente) throws Exception {
		if (!this.findById(paciente.getId()).isPresent()) {
			throw new ObjectNotFoundException();
		}
		this.repositorio.delete(paciente);
	}

}
