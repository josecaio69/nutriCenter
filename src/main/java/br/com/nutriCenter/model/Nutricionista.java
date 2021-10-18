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
@Table(name = "tb_nutricionista")
public class Nutricionista extends Usuario {

	private static final long serialVersionUID = 1L;

	@Column(name = "crn", nullable = false, unique = true)
	private String CRN;
	@Column(name = "carga_horaria")
	private int cargaHoraria;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> especializacoes;
	private String especialidade;

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public List<String> getEspecializacoes() {
		return especializacoes;
	}

	public void setEspecializacoes(List<String> especializacoes) {
		this.especializacoes = especializacoes;
	}

	public Nutricionista() {
		super.setNivelDeAcesso(2);
	}

	public String getCRN() {
		return CRN;
	}

	public void setCRN(String crn) {
		CRN = crn;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

}
