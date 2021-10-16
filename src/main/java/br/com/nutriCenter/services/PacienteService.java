package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.repository.PacienteRepository;
/**
 * @author José Caio
 *
 */

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repositorio;

	public Optional<Paciente> findById(long id) throws Exception {
		if (this.isExist(id)) {
			return this.repositorio.findById(id);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public List<Paciente> findAll() throws Exception {
		return repositorio.findAll();
	}

	public Paciente update(Paciente paciente) throws Exception {
		if (this.isExist(paciente.getId())) {
			return repositorio.save(paciente);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public Paciente updateById(long id, Paciente paciente) throws Exception {
		if (this.isExist(paciente.getId())) {
			var patient = this.repositorio.findById(id).get();
			patient.setNome(paciente.getNome());
			patient.setSobreNome(paciente.getSobreNome());
			patient.setEmail(paciente.getEmail());
			patient.setCell(paciente.getCell());
			patient.setCpf(paciente.getCpf());
			patient.setDataNasc(paciente.getDataNasc());
			return repositorio.save(patient);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public Paciente create(Paciente paciente) throws Exception {
		return repositorio.save(paciente);
	}

	public void delete(Paciente paciente) throws Exception {
		if (this.isExist(paciente.getId())) {
			this.repositorio.delete(paciente);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public void deleteById(long id) throws Exception {
		if (this.isExist(id)) {
			this.repositorio.deleteById(id);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	/*Deletar todos os pacientes do sistema*/
	public void deleteAll() throws Exception {
		this.repositorio.deleteAll();
	}

	/* Verificar a existencia de um objeto do tipo paciente no bd */
	private boolean isExist(long id) {
		if (!this.repositorio.findById(id).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
