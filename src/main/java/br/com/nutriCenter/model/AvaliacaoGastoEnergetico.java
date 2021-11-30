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
	@Column(name = "peso_paciente", nullable = false)
	private float peso;
	@Column(name = "altura_paciente", nullable = false)
	private float  altura;

	@Column(name = "nivel_de_atividade_fisica", nullable = false)
	private String nivelDeAtividadeFisica;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> patologia;

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getNivelDeAtividadeFisica() {
		return nivelDeAtividadeFisica;
	}

	public void setNivelDeAtividadeFisica(String nivelDeAtividadeFisica) {
		this.nivelDeAtividadeFisica = nivelDeAtividadeFisica;
	}

	public List<String> getPatologia() {
		return patologia;
	}

	public void setPatologia(List<String> patologia) {
		this.patologia = patologia;
	}
}
