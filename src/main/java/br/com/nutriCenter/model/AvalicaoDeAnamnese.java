/**
 * 
 */
package br.com.nutriCenter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

/**
 * @author José Caio
 *
 */
@Entity
@Table(name = "tb_avaliacao_anamnese")
public class AvalicaoDeAnamnese extends AvaliacaoNutricional {
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
