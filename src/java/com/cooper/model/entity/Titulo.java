package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

public class Titulo implements Serializable {
    public static final String STATUS_PAGAR = "Pagar";
    public static final String STATUS_PAGO = "Pago";
    public static final String TIPO_COMPRA = "Compra";
    public static final String TIPO_VENDA = "Venda";
    private int id;
    private Date dataCriacao;
    private Date dataVencimento;
    private String status;
    private String tipo;
    private double valor;
    private Associado associado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @XSSFilter
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @NotNull
    @XSSFilter
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @NotNull
    @XSSFilter
    @Length(max = 30)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull
    @XSSFilter
    @Length(max = 30)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @NotNull
    @XSSFilter
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @NotNull
    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }
}