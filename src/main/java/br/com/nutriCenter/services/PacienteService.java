package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repositorio;
	
	public Optional<Paciente> findById(long id) {
		return repositorio.findById(id);
	}
	
	public List<Paciente> findAll(){
		return repositorio.findAll();
	}
	
	public Paciente update (Paciente paciente) {
		return repositorio.save(paciente);
	}
	
	public Paciente create(Paciente paciente) {
		return repositorio.save(paciente);
	}
	
	public void delete(Paciente paciente) {
		repositorio.delete(paciente);
	}
	
	
}
