/**
 *
 */
package br.com.nutriCenter.DTO;

import java.util.Date;
import java.util.List;

import br.com.nutriCenter.model.AvaliacaoNutricional;

/**
 * @author José Caio
 *
 */
public class PacienteDTO {

    private long id;
    private String nome;
    private String sobreNome;
    private Date dataNasc;
    private String cell;
    private int nivelDeAcesso;
    private String genero;
    private String cpf;
    private String cidade;
    private String rua;
    private String bairro;
    private String estado;
    private Date dataCadastro;
    private Date dataUltimaConsulta;
    private List<AvaliacaoNutricional> avaliacoesDoPaciente;

    public PacienteDTO(long id, String nome, String sobreNome, Date dataNasc, String cell, int nivelDeAcesso,
                       String genero, String cpf, String cidade, String rua, String bairro, String estado, Date dataCadastro,
                       Date dataUltimaConsulta, List<AvaliacaoNutricional> avaliacoesDoPaciente) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNasc = dataNasc;
        this.cell = cell;
        this.nivelDeAcesso = nivelDeAcesso;
        this.genero = genero;
        this.cpf = cpf;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.estado = estado;
        this.dataCadastro = dataCadastro;
        this.dataUltimaConsulta = dataUltimaConsulta;
        this.avaliacoesDoPaciente = avaliacoesDoPaciente;
    }


    public PacienteDTO() {

    }

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


}
