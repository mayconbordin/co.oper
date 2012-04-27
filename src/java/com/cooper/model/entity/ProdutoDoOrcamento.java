package com.cooper.model.entity;

import java.io.Serializable;

public class ProdutoDoOrcamento implements Serializable {
    private ProdutoDoOrcamentoPk primaryKey;
    private double quantidade;

    public ProdutoDoOrcamento() {}

    public ProdutoDoOrcamento(Produto produto, Orcamento orcamento, double quantidade) {
        this.primaryKey = new ProdutoDoOrcamentoPk(produto, orcamento);
        this.quantidade = quantidade;
    }

    public ProdutoDoOrcamento(Produto produto, double quantidade) {
        this.primaryKey = new ProdutoDoOrcamentoPk(produto);
        this.quantidade = quantidade;
    }

    public ProdutoDoOrcamentoPk getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ProdutoDoOrcamentoPk primaryKey) {
        this.primaryKey = primaryKey;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}