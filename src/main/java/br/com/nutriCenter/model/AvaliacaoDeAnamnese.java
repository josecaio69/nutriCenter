/**
 * 
 */
package br.com.nutriCenter.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author José Caio
 *
 */
@Entity
@Table(name = "tb_avaliacao_anamnese")
public class AvaliacaoDeAnamnese extends AvaliacaoNutricional {


	@NotNull(message = "Informe a descrição da avaliação")
	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/*
	@Column(name = "restricao_alimentar")
	private String restricaoAlimentar;
	@Column(name = "fumante")
	private boolean isFumante;
	@Column(name = "consome_alcool")
	private boolean ingereAlcool;
	@Column(name = "frequencia_que_consome_alcool")
	private String frequenciaQueIngereAlcool;
	@Column(name = "patologia")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> patologia;
	@Column(name = "medicamentos_que_consome")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> medicamento;
	@Column(name = "doencas_hereditarias")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> doencasHereditarias;

	*/

}
