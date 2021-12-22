package br.com.nutriCenter.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.services.LoginService;

@RestController
@RequestMapping(value = "/api")
public class LoginResource {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> recuperarUser(@RequestBody Login login) {
        try {
            var user = loginService.logarUsuario(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ObjectNotFoundException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PasswordInvalidException erro) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recuperarSenha")
    public ResponseEntity<?> recuperarPassword(@RequestBody Login login){
        try {
            loginService.recoveyPassword(login);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ObjectNotFoundException error){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception erro){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
