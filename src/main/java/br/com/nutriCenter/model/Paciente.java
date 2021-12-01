package br.com.nutriCenter.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

/**
 * @author José Caio
 */

@Entity
@Table(name = "tb_paciente")
public class Paciente extends Usuario {

    private static final long serialVersionUID = 1L;
    @NotBlank
    @CPF
    @Column(name = "CPF")
    private String cpf;
    private String cidade;
    private String rua;
    private String bairro;
    private String estado;
    private Date dataCadastro;
    private Date dataUltimaConsulta;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<AvaliacaoNutricional> avaliacoesDoPaciente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consulta> minhasConsultas;
    @Column(name = "status_do_paciente", nullable = false)
    private boolean status;
    @Column(name = "exames_do_paciente")
    private String exame;

    public Paciente() {
        super.setNivelDeAcesso(1);
    }


    public Paciente(long id, String nome, String sobreNome, Date dataNasc, String cell, int nivelDeAcesso,
                    String genero, String cpf, String cidade, String rua, String bairro, String estado, Date dataCadastro,
                    Date dataUltimaConsulta, List<AvaliacaoNutricional> avaliacoesDoPaciente) {

        this.setId(id);
        this.setNome(nome);
        this.setSobreNome(sobreNome);
        this.setDataNasc(dataNasc);
        this.setCell(cell);
        this.setNivelDeAcesso(nivelDeAcesso);
        this.setGenero(genero);
        this.setCpf(cpf);
        this.setCidade(cidade);
        this.setRua(rua);
        this.setBairro(bairro);
        this.setEstado(estado);
        this.setDataCadastro(dataCadastro);
        this.setDataUltimaConsulta(dataUltimaConsulta);
        this.setAvaliacoesDoPaciente(avaliacoesDoPaciente);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataUltimaConsulta() {
        return dataUltimaConsulta;
    }

    public void setDataUltimaConsulta(Date dataUltimaConsulta) {
        this.dataUltimaConsulta = dataUltimaConsulta;
    }

    public List<AvaliacaoNutricional> getAvaliacoesDoPaciente() {
        return avaliacoesDoPaciente;
    }

    public void setAvaliacoesDoPaciente(List<AvaliacaoNutricional> avaliacoesDoPaciente) {
        this.avaliacoesDoPaciente = avaliacoesDoPaciente;
    }

    public List<Consulta> getMinhasConsultas() {
        return minhasConsultas;
    }

    public void setMinhasConsultas(List<Consulta> minhasConsultas) {
        this.minhasConsultas = minhasConsultas;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }
}
