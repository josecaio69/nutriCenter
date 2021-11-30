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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@NotBlank(message = "O E-mail é Obrigatorio, deve ser único e válido")
	@Email
	@Column(name = "email_login", unique = true)
	private String email;

	@NotBlank(message = "A Senha é obrigatório")
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

	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sobreNome
	 */
	public String getSobreNome() {
		return sobreNome;
	}

	/**
	 * @param sobreNome the sobreNome to set
	 */
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	/**
	 * @return the dataNasc
	 */
	public Date getDataNasc() {
		return dataNasc;
	}

	/**
	 * @param dataNasc the dataNasc to set
	 */
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the cell
	 */
	public String getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}

	/**
	 * @return the nivelDeAcesso
	 */
	public int getNivelDeAcesso() {
		return nivelDeAcesso;
	}

	/**
	 * @param nivelDeAcesso the nivelDeAcesso to set
	 */
	public void setNivelDeAcesso(int nivelDeAcesso) {
		this.nivelDeAcesso = nivelDeAcesso;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

}
