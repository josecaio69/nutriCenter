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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	@Length(min = 2, message = "É obrigatorio um nome para avaliação")
	@Column(name = "nome_da_avalicao")
	private String nomeDaAvaliacao;
	
	@NotNull(message = "Informe a descrição da avaliação")
	@Column(name = "descricao_ou_observacoes")
	private String descricao;
	
	@NotNull(message = "A data não pode ser nula")
	@Column(name = "data_de_realizacao")
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeDaAvaliacao() {
		return nomeDaAvaliacao;
	}

	public void setNomeDaAvaliacao(String nomeDaAvaliacao) {
		this.nomeDaAvaliacao = nomeDaAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
