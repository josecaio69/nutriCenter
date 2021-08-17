package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name ="tb_nutricionista")
public class Nutricionista extends Usuario {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "crn")
	private String CRN;
	@Column(name = "carga_horaria")
	private int cargaHoraria;

	public Nutricionista() {
		super.setNivelDeAcesso(2);
	}

	public String getCRN() {
		return CRN;
	}

	public void setCRN(String cRN) {
		CRN = cRN;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

}
