package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Refeicao {
	
	
	@NotNull(message = "Informe os dias desta avaliação")
	@Column(name="horario")
	private String horario;
	
	@NotNull(message = "Informe o nome da refeição")
	@Column(name="refeicao")
	private String refeicao;
	
	@NotNull(message = "Informe os alimentos desta refeição")
	@Column(name="alimentos")
	private String alimentos;

	
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(String refeicao) {
		this.refeicao = refeicao;
	}

	public String getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(String alimentos) {
		this.alimentos = alimentos;
	}
	
	
	
	
}
