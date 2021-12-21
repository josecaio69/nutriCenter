package br.com.nutriCenter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.LoginInvalidException;
import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.repository.PacienteRepository;

/**
 * @author José Caio
 */

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repositorio;

    @Autowired
    private PasswordEncoder enconder;

    @Autowired
    private NutricionistaService nutricionistaService;

    public Optional<Paciente> findById(long id) throws Exception {
        if (this.isExist(id)) {
            return this.repositorio.findById(id);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    public Optional<Paciente> findByEmail(Login login) throws Exception {
        Optional<Paciente> user = Optional.empty();
        user=(this.repositorio.findByEmail(login.getUserName()));
        var verifySenha = this.enconder.matches(login.getPassword(),user.get().getSenha());
        if(verifySenha){
            return user;
        }else if(!verifySenha){
            throw new PasswordInvalidException();
        }
        throw new LoginInvalidException();
    }

    public Optional<Paciente> findByCpf(String cpf) throws Exception {
        if (this.repositorio.findByCpf(cpf).isEmpty()) {
            throw new ObjectNotFoundException();
        } else {
            return this.repositorio.findByCpf(cpf);
        }
    }

    public List<Paciente> findAll() throws Exception {
        return repositorio.findAll();
    }

    public Paciente update(Paciente paciente) throws Exception {
        if (this.isExist(paciente.getId())) {
        	paciente.setSenha(enconder.encode(paciente.getSenha()));
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
        String senha = enconder.encode(paciente.getSenha());
        paciente.setSenha(senha);
        return repositorio.save(paciente);
    }

    /*Associar paciente ao nutricionista*/
    public Paciente createNewPaciente(long idNutricionista, Paciente paciente) throws Exception {
        var nutricionista = this.nutricionistaService.findById(idNutricionista).get();
        if (nutricionista.equals(null))
            throw new ObjectNotFoundException();
        else {
            List<Paciente> pacienteList = nutricionista.getPacientes();
            pacienteList.add(paciente);
            nutricionista.setPacientes(pacienteList);
            this.nutricionistaService.update(nutricionista);
        }
        return paciente;
    }

    public Optional<Paciente> findByEmail(String email) throws Exception{
        return Optional.ofNullable(this.repositorio.findByEmail(email).orElseThrow(
                ObjectNotFoundException::new));
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
    public boolean isExist(long id){
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
