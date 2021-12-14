package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "avalicao_exames")
public class Exame extends AvaliacaoNutricional{
	
	@NotNull(message = "Informe a descrição da avaliação")
	@Column(name = "Exames")
	private String exames;
	
	private String observacoes;

	public String getExames() {
		return exames;
	}

	public void setExames(String exames) {
		this.exames = exames;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
}
