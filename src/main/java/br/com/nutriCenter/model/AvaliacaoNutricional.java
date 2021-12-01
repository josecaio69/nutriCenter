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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author José Caio
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AvaliacaoNutricional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	@Length(min = 2, message = "É obrigatorio um nome para avaliação")
	@Column(name = "tipo_de_avalicao")
	private String tipo;

	@NotBlank
	@Length(min = 2, message = "É obrigatorio um nome para avaliação")
	@Column(name = "tipo_da_avalicao")
	private String titulo;
	
	@NotNull(message = "A data não pode ser nula")
	@Column(name = "data_de_realizacao")
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
