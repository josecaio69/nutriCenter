/**
 * 
 */
package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author José Caio
 *
 */
@Entity
@Table(name = "avalicao_antropometrica")
public class AvaliacaoAntropometrica extends AvaliacaoNutricional {

	@Column(name = "peso_ideal")
	private float pesoIdeal;

	@Column(name = "paciente_disponivel")
	private boolean isDisponivel;

	/**
	 * @return the pesoIdeal
	 */
	public float getPesoIdeal() {
		return pesoIdeal;
	}

	/**
	 * @param pesoIdeal the pesoIdeal to set
	 */
	public void setPesoIdeal(float pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}

	/**
	 * @return the isDisponivel
	 */
	public boolean isDisponivel() {
		return isDisponivel;
	}

	/**
	 * @param isDisponivel the isDisponivel to set
	 */
	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

}
