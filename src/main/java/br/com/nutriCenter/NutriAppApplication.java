package br.com.nutriCenter;

import br.com.nutriCenter.model.Nutricionista;
import br.com.nutriCenter.services.LoginService;
import br.com.nutriCenter.services.NutricionistaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Optional;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NutriAppApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NutriAppApplication.class, args);
    }

}
