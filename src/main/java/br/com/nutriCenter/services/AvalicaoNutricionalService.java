/**
 * 
 */
package br.com.nutriCenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.InvalidNutritionalAssessmentException;
import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.AvaliacaoNutricional;

/**
 * @author José Caio
 *
 */
@Service
public class AvalicaoNutricionalService {

	@Autowired
	private PacienteService pacienteService;

	public AvaliacaoNutricional adicionarAvaliacao(long id, AvaliacaoNutricional avaliacao) throws Exception {
		if (avaliacao.equals(null)) {
			throw new InvalidNutritionalAssessmentException();
		} else if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();

			List<AvaliacaoNutricional> avaliacoesDestePaciente = paciente.getAvaliacoesDoPaciente();
			avaliacoesDestePaciente.add(avaliacao);
			paciente.setAvaliacoesDoPaciente(avaliacoesDestePaciente);
			this.pacienteService.update(paciente);

			return avaliacao;
		}

	}

}
