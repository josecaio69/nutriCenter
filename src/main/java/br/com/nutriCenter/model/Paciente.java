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
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Nutricionista profissionalDeNutricao;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consulta> minhasConsultas;

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


    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the dataUltimaConsulta
     */
    public Date getDataUltimaConsulta() {
        return dataUltimaConsulta;
    }

    /**
     * @param dataUltimaConsulta the dataUltimaConsulta to set
     */
    public void setDataUltimaConsulta(Date dataUltimaConsulta) {
        this.dataUltimaConsulta = dataUltimaConsulta;
    }

    /**
     * @return the avaliacoesDoPaciente
     */
    public List<AvaliacaoNutricional> getAvaliacoesDoPaciente() {
        return avaliacoesDoPaciente;
    }

    /**
     * @param avaliacoesDoPaciente the avaliacoesDoPaciente to set
     */
    public void setAvaliacoesDoPaciente(List<AvaliacaoNutricional> avaliacoesDoPaciente) {
        this.avaliacoesDoPaciente = avaliacoesDoPaciente;
    }

    public Nutricionista getProfissionalDeNutricao() {
        return profissionalDeNutricao;
    }

    public void setProfissionalDeNutricao(Nutricionista profissionalDeNutricao) {
        this.profissionalDeNutricao = profissionalDeNutricao;
    }

    public List<Consulta> getMinhasConsultas() {
        return minhasConsultas;
    }

    public void setMinhasConsultas(List<Consulta> minhasConsultas) {
        this.minhasConsultas = minhasConsultas;
    }
}
