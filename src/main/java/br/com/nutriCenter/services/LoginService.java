package br.com.nutriCenter.services;

import br.com.nutriCenter.exception.LoginInvalidException;
import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.*;
import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    private Usuario user;

/*
FALTA TER%MINAR
 */
    public Usuario logarUsuario(Login login) throws Exception {
        if(this.nutricionistaService.searchForEmail(login.getUserName()))
            return nutricionistaService.findByEmail(login).get();

        if(this.pacienteService.searchForEmail(login.getUserName()))
            return pacienteService.findByEmail(login).get();

        if(this.administradorService.searchForEmail(login.getUserName())) {
            return administradorService.findByEmail(login).get();
        }

    throw new ObjectNotFoundException();
    }




}
