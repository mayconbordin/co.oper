package com.cooper.model.entity;

import java.io.Serializable;

public class ProdutoNaLoja implements Serializable {
    private ProdutoNaLojaPk primaryKey;
    private double quantidade;

    public ProdutoNaLojaPk getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ProdutoNaLojaPk primaryKey) {
        this.primaryKey = primaryKey;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

}