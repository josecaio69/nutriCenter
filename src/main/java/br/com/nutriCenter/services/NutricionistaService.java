package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.repository.NutricionistaRepository;

/**
 * @author José Caio
 *
 */

@Service
public class NutricionistaService {

	@Autowired
	private NutricionistaRepository repositorio;

	public Nutricionista createNutricionista(Nutricionista entidade) {
		return repositorio.save(entidade);
	}

	public List<Nutricionista> findAll() throws Exception {
		return this.repositorio.findAll();
	}

	public Optional<Nutricionista> findById(Long id) throws Exception {
		if (!this.repositorio.findById(id).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			return this.repositorio.findById(id);

		}
	}

	public void delete(Nutricionista nutricionista) throws Exception {
		if (!this.repositorio.findById(nutricionista.getId()).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			this.repositorio.delete(nutricionista);
		}
	}

	public void deleteAll() throws Exception {
		this.repositorio.deleteAll();
	}

	public Nutricionista update(Nutricionista nutricionista) throws Exception {
		if (!this.repositorio.findById(nutricionista.getId()).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			return this.repositorio.save(nutricionista);

		}
	}

	/* UPDATE pelo o id do objeto */
	public Nutricionista updateById(long id, Nutricionista nutricionista) throws Exception {
		if (!this.repositorio.findById(id).isPresent()) {
			throw new ObjectNotFoundException();
		} else {
			var profissionalDeNutricao = this.repositorio.findById(id).get();
			profissionalDeNutricao.setNome(nutricionista.getNome());
			profissionalDeNutricao.setSobreNome(nutricionista.getSobreNome());
			profissionalDeNutricao.setDataNasc(nutricionista.getDataNasc());
			profissionalDeNutricao.setCpf(nutricionista.getCpf());
			profissionalDeNutricao.setEmail(nutricionista.getEmail());
			profissionalDeNutricao.setCell(nutricionista.getCell());
			profissionalDeNutricao.setNivelDeAcesso(nutricionista.getNivelDeAcesso());
			profissionalDeNutricao.setCargaHoraria(nutricionista.getCargaHoraria());
			profissionalDeNutricao.setCRN(nutricionista.getCRN());
			return this.repositorio.save(nutricionista);

		}
	}

	/* GET busca pelo nome ou parte do nome de um ou vários nutricionistas */

	public List<Nutricionista> findByName(String nome) throws Exception {
		if (nome.equals(null)) {
			return this.findAll();
		} else {
			return this.repositorio.findByNomeContaining(nome);
		}
	}

}
