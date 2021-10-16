package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * @author José Caio
 *
 */

@Entity
@Table(name ="tb_admin")
public class Administrador extends Usuario {

	private static final long serialVersionUID = 1L;

	@Column(name = "carga_horaria")
	private int cargaHoraria;

	public Administrador() {
		super.setNivelDeAcesso(3);
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

}
