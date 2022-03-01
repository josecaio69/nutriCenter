package br.com.nutriCenter.services;

import org.springframework.beans.factory.annotation.Autowired;
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
	private EnverEmailService emailService;

	public Usuario logarUsuario(Login login) throws Exception {

		switch (login.getNivelDeAcesso()) {
		case 1:
			if (this.pacienteService.searchForEmail(login.getUserName()))
				return pacienteService.findByEmail(login).get();
			break;
			
		case 2:
			if (this.nutricionistaService.searchForEmail(login.getUserName()))
				return nutricionistaService.findByEmail(login).get();
			break;
		case 3:
			if (this.administradorService.searchForEmail(login.getUserName()))
				return administradorService.findByEmail(login).get();
			break;
		default:
			break;
		}
		throw new ObjectNotFoundException();
	}

	public void recoveyPassword(Login login) throws Exception {
		switch (login.getNivelDeAcesso()) {
		case 1:
			if (this.pacienteService.searchForEmail(login.getUserName())) {
				Paciente paciente = this.pacienteService.findByEmail(login.getUserName()).get();
				String passwordTemp = emailService.gerarSenhaAleatoria();
				paciente.setSenha(passwordTemp);
				paciente.setSenhaTemp(true);
				System.out.print("\u001b[32m" + passwordTemp + "\n" + login.getUserName() + "\u001b[0m");
				emailService.sendSimpleMessage(login.getUserName(), passwordTemp);
				pacienteService.update(paciente);
				return;
			}
		case 2:
			if (this.nutricionistaService.searchForEmail(login.getUserName())) {
				Nutricionista nutricionista = this.nutricionistaService.findByEmail(login.getUserName()).get();
				String passwordTemp = emailService.gerarSenhaAleatoria();
				nutricionista.setSenha(passwordTemp);
				nutricionista.setSenhaTemp(true);
				System.out.print("\u001b[32m" + passwordTemp + "\n" + login.getUserName() + "\u001b[0m");
				emailService.sendSimpleMessage(login.getUserName(), passwordTemp);
				nutricionistaService.update(nutricionista);
				return;
			}
		case 3:
			if (this.administradorService.searchForEmail(login.getUserName())) {
				Administrador adm = this.administradorService.findByEmail(login.getUserName()).get();
				String passwordTemp = emailService.gerarSenhaAleatoria();
				adm.setSenha(passwordTemp);
				adm.setSenhaTemp(true);
				System.out.print("\u001b[32m" + passwordTemp + "\n" + login.getUserName() + "\u001b[0m");
				emailService.sendSimpleMessage(login.getUserName(), passwordTemp);
				administradorService.update(adm);
				return;
			}
		default:
			break;
		}
		throw new ObjectNotFoundException();
	}

}
