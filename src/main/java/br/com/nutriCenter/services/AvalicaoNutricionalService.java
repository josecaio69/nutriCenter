/**
 * 
 */
package br.com.nutriCenter.services;

import java.util.ArrayList;
import java.util.List;

import br.com.nutriCenter.model.AvaliacaoAntropometrica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriCenter.exception.InvalidNutritionalAssessmentException;
import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.AvaliacaoNutricional;
import br.com.nutriCenter.model.AvaliacaoDeAnamnese;

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
	
	public AvaliacaoNutricional atualizarAvalicao(long id, AvaliacaoNutricional avaliacao) throws Exception{
		if (avaliacao.equals(null)) {
			throw new InvalidNutritionalAssessmentException();
		} else if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> avaliacoesDestePaciente = paciente.getAvaliacoesDoPaciente();
			for(AvaliacaoNutricional  a : avaliacoesDestePaciente) {
				if(a.getId()==avaliacao.getId()) {
					avaliacoesDestePaciente.remove(a);
					avaliacoesDestePaciente.add(avaliacao);
					break;
				}
					
			}
			paciente.setAvaliacoesDoPaciente(avaliacoesDestePaciente);
			this.pacienteService.update(paciente);

			return avaliacao;
		}
		
	}
	
	public AvaliacaoNutricional getAvaliacao(long id, long idAvaliacao) throws Exception{
		if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> lista = paciente.getAvaliacoesDoPaciente();
			AvaliacaoNutricional ava = null;
			
			for(AvaliacaoNutricional a : lista) {
				if(a.getId()==idAvaliacao) {
					ava = a;
					break;
				}
			}
			if (ava==null) {
				throw new ObjectNotFoundException();
			}else {
				return ava;
			}
		}
	}
	
	
	public void deleteAvaliacao(long id, long idAvaliacao) throws Exception{
		if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> lista = paciente.getAvaliacoesDoPaciente();
			for(AvaliacaoNutricional a : lista) {
				if(a.getId()==idAvaliacao) {
					lista.remove(a);
					break;
				}
			}
			paciente.setAvaliacoesDoPaciente(lista);
			this.pacienteService.update(paciente);
		}
	}
	
	
	public List<AvaliacaoNutricional> listarAvaliacoes(long id)  throws Exception{
		if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> lista = paciente.getAvaliacoesDoPaciente();
			return lista;
		}
	}
	
	
	/*
	 * os metodos daqui para cima não precisar ser diferenciados pelo tipo de avaliação AQUI NO SERVICE
	 * 
	 * =========================================================================================
	 * 
	 * os metodos daqui para baixo precisam ser diferenciados pelo tipo AQUI NO SERVICE
	 * (no caso serão apenas os metodos de listar por tipo de avaliação)
	 */
	

	public List<AvaliacaoDeAnamnese> listarAnamnese(long id) throws Exception{
		if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {

			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> avaliacoesDestePaciente = paciente.getAvaliacoesDoPaciente();
			List<AvaliacaoDeAnamnese> anamnese = new ArrayList<AvaliacaoDeAnamnese>();
			for(AvaliacaoNutricional a : avaliacoesDestePaciente) {
				if(a.getTipo().equals("anamnese")) {
					anamnese.add((AvaliacaoDeAnamnese) a);
				}
					
			}
			return anamnese;
		}
	}
	

	
	public List<AvaliacaoAntropometrica> listarAntropometrica(long id) throws Exception{
		if (this.pacienteService.findById(id).isEmpty()) {
			throw new ObjectNotFoundException();
		} else {
			
			var paciente = this.pacienteService.findById(id).get();
			List<AvaliacaoNutricional> avaliacoesDestePaciente = paciente.getAvaliacoesDoPaciente();
			List<AvaliacaoAntropometrica> antropometrica = new ArrayList<AvaliacaoAntropometrica>();
			for(AvaliacaoNutricional a : avaliacoesDestePaciente) {
				if(a.getTipo().equals("antropometrica")) {
					antropometrica.add((AvaliacaoAntropometrica) a);
				}
				
			}
			return antropometrica;
		}
	}

}
