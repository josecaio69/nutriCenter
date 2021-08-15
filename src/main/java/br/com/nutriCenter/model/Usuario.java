package br.com.nutriCenter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "sobre_nome")
	private String sobreNome;
	@Column(name = "data_nascimento")
	private Date dataNasc;
	@Column(name = "CPF")
	private String cpf;
	@Column(name = "email")
	private String email;
	@Column(name = "celular")
	private String cell;
	@Column(name = "prioridade_de_acesso")
	private int nivelDeAcesso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public int getNivelDeAcesso() {
		return nivelDeAcesso;
	}

	public void setNivelDeAcesso(int nivelDeAcesso) {
		this.nivelDeAcesso = nivelDeAcesso;
	}

}
