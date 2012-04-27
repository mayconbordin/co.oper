package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.validator.constraints.Length;

public class Orcamento implements Serializable {
    public static final String STATUS_PENDENTE = "pendente";
    public static final String STATUS_RESPONDIDO = "respondido";

    private int id;
    private double total;
    private Associado associado;
    private String comentario;
    private String status;
    private Date data;
    private Set<ProdutoDoOrcamento> produtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @XSSFilter
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @NotNull
    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    @XSSFilter
    @Length(max = 1000)
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @NotNull
    @XSSFilter
    @Length(max = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Set<ProdutoDoOrcamento> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoDoOrcamento> produtos) {
        this.produtos = produtos;
    }
}