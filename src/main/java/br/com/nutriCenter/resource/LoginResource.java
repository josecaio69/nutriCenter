package br.com.nutriCenter.resource;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.exception.PasswordInvalidException;
import br.com.nutriCenter.model.Login;
import br.com.nutriCenter.services.EnverEmailService;
import br.com.nutriCenter.services.LoginService;
import br.com.nutriCenter.services.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/login")
public class LoginResource {
    @Autowired
    private NutricionistaService nutricionistaService;
    @Autowired
    private EnverEmailService enverEmailService;
    @Autowired
    private LoginService loginService;

    @GetMapping()
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

    @GetMapping("/recuperarSenha")
    public ResponseEntity<?> recuperarPassword(@RequestBody Login login){
        try {
            loginService.recoveyPassword(login.getUserName());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ObjectNotFoundException error){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception erro){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
