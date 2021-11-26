/**
 * 
 */
package br.com.nutriCenter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author José Caio
 *
 */
@XmlTransient 
@XmlSeeAlso({AvaliacaoNutricional.class})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AvaliacaoNutricional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	@Length(min = 2, message = "É obrigatorio um nome para avaliaação")
	@Column(name = "nome_da_avalicao")
	private String nomeDaAvaliacao;
	@Column(name = "titulo_da_avalicao")
	private String titulo;
	@Column(name = "descricao_ou_observacoes")
	private String descricao;
	@Column(name = "data_de_realizacao")
	private Date data;
	@Column(name = "altura_do_paciente")
	private float altura;
	@Column(name = "peso_do_paciente")
	private float peso;

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
	 * @return the nomeDaAvaliacao
	 */
	public String getNomeDaAvaliacao() {
		return nomeDaAvaliacao;
	}

	/**
	 * @param nomeDaAvaliacao the nomeDaAvaliacao to set
	 */
	public void setNomeDaAvaliacao(String nomeDaAvaliacao) {
		this.nomeDaAvaliacao = nomeDaAvaliacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the altura
	 */
	public float getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(float altura) {
		this.altura = altura;
	}

	/**
	 * @return the peso
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

}
