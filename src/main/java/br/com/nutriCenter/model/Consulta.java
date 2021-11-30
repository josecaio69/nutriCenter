package br.com.nutriCenter.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="tb_consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name ="data_da_consulta")
    private Date dataDaConsulta;
    @Column(name = "valor_da_consulta")
    private BigDecimal valor;
    @Column(name = "horario_da_consulta")
    private LocalDateTime horaDaConsulta;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_do_paciente")
    private Paciente paciente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataDaConsulta() {
        return dataDaConsulta;
    }

    public void setDataDaConsulta(Date dataDaConsulta) {
        this.dataDaConsulta = dataDaConsulta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getHoraDaConsulta() {
        return horaDaConsulta;
    }

    public void setHoraDaConsulta(LocalDateTime horaDaConsulta) {
        this.horaDaConsulta = horaDaConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
