package br.com.nutriCenter.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;

public class AvaliacaoDeSuplementacao extends AvaliacaoNutricional{
    @Column(name ="suplementos_prescritos")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> sumplementos;

    @Column(name ="elementos_fitoterapicos")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> fitoterapicos;
    @Column(name = "posologia", nullable = false)
    private String posologia;

    public List<String> getSumplementos() {
        return sumplementos;
    }

    public void setSumplementos(List<String> sumplementos) {
        this.sumplementos = sumplementos;
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