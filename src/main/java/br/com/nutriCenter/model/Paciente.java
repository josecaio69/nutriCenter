package br.com.nutriCenter.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Paciente extends Usuario {

	private static final long serialVersionUID = 1L;
	
	private String cidade;
	private String rua;
	private String bairro;
	private String estado;
	private Date dataCadastro;
	private Date dataUltimaConsulta;

	public Paciente() {
		super.setNivelDeAcesso(1);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUltimaConsulta() {
		return dataUltimaConsulta;
	}

	public void setDataUltimaConsulta(Date dataUltimaConsulta) {
		this.dataUltimaConsulta = dataUltimaConsulta;
	}

}
