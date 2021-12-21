package br.com.nutriCenter.services;

import br.com.nutriCenter.exception.LoginInvalidException;
import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.repository.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author José Caio
 */

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository repositorio;
    @Autowired
    private PasswordEncoder enconder;

    public Nutricionista createNutricionista(Nutricionista entidade) {
        String senha = enconder.encode(entidade.getSenha());
        entidade.setSenha(senha);
        return repositorio.save(entidade);

    }

    public Optional<Nutricionista> findByEmail(Login login) throws Exception {
        Optional<Nutricionista> user = Optional.empty();
        user = repositorio.findByEmail(login.getUserName());
        var verifySenha = this.enconder.matches(login.getPassword(),user.get().getSenha());
        if(verifySenha){
            return user;
        }else if(!verifySenha){
            throw new PasswordInvalidException();
        }
        throw new LoginInvalidException();
    }

    public Optional<Nutricionista> findByEmail(String email) throws Exception{
        return Optional.ofNullable(this.repositorio.findByEmail(email).orElseThrow(
                ObjectNotFoundException::new));
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

    public void deleteById(long id) throws Exception {
        if (!this.repositorio.findById(id).isPresent()) {
            throw new ObjectNotFoundException();
        } else {
            this.repositorio.deleteById(id);
        }
    }

    public void deleteAll() throws Exception {
        this.repositorio.deleteAll();
    }

    public Nutricionista update(Nutricionista nutricionista) throws Exception {
        if (!this.repositorio.findById(nutricionista.getId()).isPresent()) {
            throw new ObjectNotFoundException();
        } else {
        	nutricionista.setSenha(enconder.encode(nutricionista.getSenha()));
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

    public boolean isExist(long id) {
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
