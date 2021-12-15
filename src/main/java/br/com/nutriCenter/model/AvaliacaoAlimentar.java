package br.com.nutriCenter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="avaliacao_alimentar")
public class AvaliacaoAlimentar extends AvaliacaoNutricional{
	
	
	@NotNull(message = "Informe os dias desta avaliação")
	@Column(name = "dias")
	private String dias;
	
	@ElementCollection
	@NotNull(message = "Informe os dias desta avaliação")
	private List<Refeicao> refeicao;
	
	@Column(name = "observacoes")
	private String observacoes;

	
	
	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public List<Refeicao> getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(List<Refeicao> refeicao) {
		this.refeicao = refeicao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
