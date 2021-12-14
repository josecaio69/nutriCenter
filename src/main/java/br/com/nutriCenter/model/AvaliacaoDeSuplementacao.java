package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "avalicao_suplementacao")
public class AvaliacaoDeSuplementacao extends AvaliacaoNutricional{

	@NotNull(message = "Informe a descrição da avaliação")
	@Column(name = "descricao")
	private String descricao;
	
    @Column(name ="suplementos")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> suplementos;

    @Column(name ="fitoterapicos")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> fitoterapicos;
    
    @Column(name = "posologia")
    private String posologia;
    
    
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getSuplementos() {
        return suplementos;
    }

    public void setSuplementos(List<String> suplementos) {
        this.suplementos = suplementos;
    }

    public List<String> getFitoterapicos() {
        return fitoterapicos;
    }

    public void setFitoterapicos(List<String> fitoterapicos) {
        this.fitoterapicos = fitoterapicos;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }
}
