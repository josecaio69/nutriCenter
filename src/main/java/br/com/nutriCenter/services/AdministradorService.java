package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import br.com.nutriCenter.exception.LoginInvalidException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.model.Nutricionista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.repository.AdministradorRepository;
/**
 * @author José Caio
 *
 */

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository repositorio;
	@Autowired
	private PasswordEncoder enconder;

	public Administrador create(Administrador adm) throws Exception {
		String senha = enconder.encode(adm.getSenha());
		adm.setSenha(senha);
		return this.repositorio.save(adm);
	}

	public Optional<Administrador> findByEmail(Login login) throws Exception {
		Optional<Administrador> user = Optional.empty();
		user=(this.repositorio.findByEmail(login.getUserName()));
		var verifySenha = this.enconder.matches(login.getPassword(),user.get().getSenha());
		if(verifySenha){
			return user;
		}else if(!verifySenha){
			throw new PasswordInvalidException();
		}
		throw new LoginInvalidException();
	}

	public Optional<Administrador> findByEmail(String email) throws Exception{
		return Optional.ofNullable(this.repositorio.findByEmail(email).orElseThrow(
				ObjectNotFoundException::new));
	}

	public List<Administrador> findAll() throws Exception {
		return repositorio.findAll();
	}

	public Optional<Administrador> findById(long id) throws Exception {
		if (this.isExist(id)) {
			return this.repositorio.findById(id);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public void delete(Administrador adm) throws Exception {
		if (this.isExist(adm.getId())) {
			this.repositorio.delete(adm);
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

	public Administrador update(Administrador adm) throws Exception {
		if (this.isExist(adm.getId())) {
			return repositorio.save(adm);
		} else {
			throw new ObjectNotFoundException();
		}
	}
	
	public Administrador updateById(long id,Administrador adm) throws Exception {
		if (this.isExist(id)) {
			var upAdm = this.repositorio.findById(id).get();
			upAdm.setNome(adm.getNome());
			upAdm.setSobreNome(adm.getSobreNome());
			upAdm.setEmail(adm.getEmail());
			upAdm.setDataNasc(adm.getDataNasc());
			upAdm.setCell(adm.getCell());
			upAdm.setCargaHoraria(adm.getCargaHoraria());
			return repositorio.save(upAdm);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	/* Verificar se um objeto adm existe no BD */
	public boolean isExist(long id) throws Exception {
		if (!this.repositorio.findById(id).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean searchForEmail(String email){
		if (!this.repositorio.findByEmail(email).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
