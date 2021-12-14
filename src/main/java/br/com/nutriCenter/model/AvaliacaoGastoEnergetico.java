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

	@Column(name = "protocolo", nullable = false)
	private String protocolo;
	
	@Column(name = "peso_paciente", nullable = false)
	private float peso;
	
	@Column(name = "altura_paciente", nullable = false)
	private float  altura;

	@Column(name = "nivel_de_atividade", nullable = false)
	private String nivelDeAtividade;
	
	@Column(name = "fator_de_injuria", nullable = false)
	private String injuria;

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

	public String getNivelDeAtividade() {
		return nivelDeAtividade;
	}

	public void setNivelDeAtividade(String nivelDeAtividade) {
		this.nivelDeAtividade = nivelDeAtividade;
	}

	public String getInjuria() {
		return injuria;
	}

	public void setInjuria(String injuria) {
		this.injuria = injuria;
	}

	
}
