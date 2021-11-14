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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * @author José Caio
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "O Nome é Obrigatorio")
	@Length(min = 2, message = "Seu nome deve ter no mínimo 2 Caracteres")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank
	@Column(name = "sobre_nome")
	private String sobreNome;
	
	@Column(name = "data_nascimento")
	private Date dataNasc;
	
	@Email
	@Column(name = "email_login",nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 8, message = "A senha deverá ter no mínimo {min} caracteres")
	private String senha;
	
	@NotBlank
	@Column(name = "celular")
	private String cell;
	
	@Column(name = "prioridade_de_acesso")
	private int nivelDeAcesso;
	
	@NotBlank
	@Column(name = "sexo")
	private String genero;

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

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
