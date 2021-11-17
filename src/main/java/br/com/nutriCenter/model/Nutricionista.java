package br.com.nutriCenter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profissionalDeNutricao")
	private List<Paciente> pacientes;

	/**
	 * @return the cRN
	 */
	public String getCRN() {
		return CRN;
	}

	/**
	 * @param cRN the cRN to set
	 */
	public void setCRN(String cRN) {
		CRN = cRN;
	}

	/**
	 * @return the cargaHoraria
	 */
	public int getCargaHoraria() {
		return cargaHoraria;
	}

	/**
	 * @param cargaHoraria the cargaHoraria to set
	 */
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	/**
	 * @return the especializacoes
	 */
	public List<String> getEspecializacoes() {
		return especializacoes;
	}

	/**
	 * @param especializacoes the especializacoes to set
	 */
	public void setEspecializacoes(List<String> especializacoes) {
		this.especializacoes = especializacoes;
	}

	/**
	 * @return the especialidade
	 */
	public String getEspecialidade() {
		return especialidade;
	}

	/**
	 * @param especialidade the especialidade to set
	 */
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	/**
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
