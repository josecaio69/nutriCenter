/**
 * 
 */
package br.com.nutriCenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.nutriCenter.model.AvaliacaoNutricional;

/**
 * @author José Caio
 *
 */
@Configuration
public class AvaliacaoNutricionalConfig {

	@Bean
	public AvaliacaoNutricional avalicao() {
		return new AvaliacaoNutricional() {
		};
	}
}
