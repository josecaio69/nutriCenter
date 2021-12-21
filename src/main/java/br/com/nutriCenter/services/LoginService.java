package br.com.nutriCenter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.model.Paciente;
import br.com.nutriCenter.model.Usuario;

@Service
public class LoginService {

    @Autowired
    private NutricionistaService nutricionistaService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private PasswordEncoder enconder;
    @Autowired
    private EnverEmailService emailService;

    public Usuario logarUsuario(Login login) throws Exception {
    	
    	switch (login.getNivelDeAcesso()) {
		case 1:
			if(this.nutricionistaService.searchForEmail(login.getUserName()))
				return pacienteService.findByEmail(login).get();
		case 2:
			if(this.pacienteService.searchForEmail(login.getUserName()))
				return nutricionistaService.findByEmail(login).get();
		case 3:
       		if(this.administradorService.searchForEmail(login.getUserName()))
       			return administradorService.findByEmail(login).get();
		default:
			break;
		}
    	throw new ObjectNotFoundException();
    }

    public void recoveyPassword(String email) throws Exception{
        if(this.nutricionistaService.searchForEmail(email)){
           Nutricionista nutricionista = this.nutricionistaService.findByEmail(email).get();
           String passwordTemp = emailService.gerarSenhaAleatoria();
           String encoderPassaword = this.enconder.encode(passwordTemp);
           nutricionista.setSenha(encoderPassaword);
           nutricionista.setSenhaTemp(true);
           emailService.sendSimpleMessage(email,passwordTemp);
           nutricionistaService.update(nutricionista);
        }

        if(this.pacienteService.searchForEmail(email)){
            Paciente paciente = this.pacienteService.findByEmail(email).get();
            String passwordTemp = emailService.gerarSenhaAleatoria();
            String encoderPassaword = this.enconder.encode(passwordTemp);
            paciente.setSenha(encoderPassaword);
            paciente.setSenhaTemp(true);
            emailService.sendSimpleMessage(email,passwordTemp);
            pacienteService.update(paciente);
        }

        if(this.administradorService.searchForEmail(email)){
            Administrador adm = this.administradorService.findByEmail(email).get();
            String passwordTemp = emailService.gerarSenhaAleatoria();
            String encoderPassaword = this.enconder.encode(passwordTemp);
            adm.setSenha(encoderPassaword);
            adm.setSenhaTemp(true);
            emailService.sendSimpleMessage(email,passwordTemp);
            administradorService.update(adm);
        }

    }

}
