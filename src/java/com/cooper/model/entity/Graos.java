package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

public class Graos implements Serializable {
    public static final String STATUS_DESCARREGADO = "Descarregado";
    public static final String STATUS_DESCARREGAR = "Descarregar";

    private int romaneio;
    private Date data;
    private double descontos;
    private String descricao;
    private String motoristaCaminhao;
    private double pesoQtde;
    private String placaCaminhao;
    private String produto;
    private String status;
    private Associado associado;

    @NotNull
    @XSSFilter
    public int getRomaneio() {
        return romaneio;
    }

    public void setRomaneio(int romaneio) {
        this.romaneio = romaneio;
    }

    @NotNull
    @XSSFilter
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @NotNull
    @XSSFilter
    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    @NotNull
    @XSSFilter
    @Length(max = 200)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    @XSSFilter
    @Length(max = 80)
    public String getMotoristaCaminhao() {
        return motoristaCaminhao;
    }

    public void setMotoristaCaminhao(String motoristaCaminhao) {
        this.motoristaCaminhao = motoristaCaminhao;
    }

    @NotNull
    @XSSFilter
    public double getPesoQtde() {
        return pesoQtde;
    }

    public void setPesoQtde(double pesoQtde) {
        this.pesoQtde = pesoQtde;
    }

    @NotNull
    @XSSFilter
    @Length(min = 7,max = 7)
    public String getPlacaCaminhao() {
        return placaCaminhao;
    }

    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }

    @NotNull
    @XSSFilter
    @Length(max = 30)
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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
    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }
}