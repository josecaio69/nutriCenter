/**
 * 
 */
package br.com.nutriCenter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

/**
 * @author José Caio
 *
 */
@Entity
@Table(name = "tb_avaliacao_anamnese")
public class AvalicaoDeAnamnese extends AvaliacaoNutricional {
	@Column(name = "restricao_alimentar")
	private String restricaoAlimentar;
	@Column(name = "fumante")
	private boolean isFumante;
	@Column(name = "consome_alcool")
	private boolean ingereAlcool;
	@Column(name = "frequencia_que_consome_alcool")
	private String frequenciaQueIngereAlcool;
	@Column(name = "patologia")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> patologia;
	@Column(name = "medicamentos_que_consome")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> medicamento;
	@Column(name = "doencas_hereditarias")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> doencasHereditarias;

	/**
	 * @return the restricaoAlimentar
	 */
	public String getRestricaoAlimentar() {
		return restricaoAlimentar;
	}

	/**
	 * @param restricaoAlimentar the restricaoAlimentar to set
	 */
	public void setRestricaoAlimentar(String restricaoAlimentar) {
		this.restricaoAlimentar = restricaoAlimentar;
	}

	/**
	 * @return the isFumante
	 */
	public boolean isFumante() {
		return isFumante;
	}

	/**
	 * @param isFumante the isFumante to set
	 */
	public void setFumante(boolean isFumante) {
		this.isFumante = isFumante;
	}

	/**
	 * @return the ingereAlcool
	 */
	public boolean isIngereAlcool() {
		return ingereAlcool;
	}

	/**
	 * @param ingereAlcool the ingereAlcool to set
	 */
	public void setIngereAlcool(boolean ingereAlcool) {
		this.ingereAlcool = ingereAlcool;
	}

	/**
	 * @return the frequenciaQueIngereAlcool
	 */
	public String getFrequenciaQueIngereAlcool() {
		return frequenciaQueIngereAlcool;
	}

	/**
	 * @param frequenciaQueIngereAlcool the frequenciaQueIngereAlcool to set
	 */
	public void setFrequenciaQueIngereAlcool(String frequenciaQueIngereAlcool) {
		this.frequenciaQueIngereAlcool = frequenciaQueIngereAlcool;
	}

	/**
	 * @return the patologia
	 */
	public List<String> getPatologia() {
		return patologia;
	}

	/**
	 * @param patologia the patologia to set
	 */
	public void setPatologia(List<String> patologia) {
		this.patologia = patologia;
	}

	/**
	 * @return the medicamento
	 */
	public List<String> getMedicamento() {
		return medicamento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(List<String> medicamento) {
		this.medicamento = medicamento;
	}

	/**
	 * @return the doencasHereditarias
	 */
	public List<String> getDoencasHereditarias() {
		return doencasHereditarias;
	}

	/**
	 * @param doencasHereditarias the doencasHereditarias to set
	 */
	public void setDoencasHereditarias(List<String> doencasHereditarias) {
		this.doencasHereditarias = doencasHereditarias;
	}

}
