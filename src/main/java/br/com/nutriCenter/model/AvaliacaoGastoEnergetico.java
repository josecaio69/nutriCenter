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
@Table(name = "avalicao_gasto_energetico")
public class AvaliacaoGastoEnergetico extends AvaliacaoNutricional {

	private String protocolo;

	@Column(name = "nivel_de_atividade_fisica")
	private String nivelDeAtividadeFisica;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> patologia;

	/**
	 * @return the protocolo
	 */
	public String getProtocolo() {
		return protocolo;
	}

	/**
	 * @param protocolo the protocolo to set
	 */
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * @return the nivelDeAtividadeFisica
	 */
	public String getNivelDeAtividadeFisica() {
		return nivelDeAtividadeFisica;
	}

	/**
	 * @param nivelDeAtividadeFisica the nivelDeAtividadeFisica to set
	 */
	public void setNivelDeAtividadeFisica(String nivelDeAtividadeFisica) {
		this.nivelDeAtividadeFisica = nivelDeAtividadeFisica;
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

}
