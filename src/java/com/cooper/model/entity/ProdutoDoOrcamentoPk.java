package com.cooper.model.entity;

import java.io.Serializable;

public class ProdutoDoOrcamentoPk implements Serializable {
    private Produto produto;
    private Orcamento orcamento;

    public ProdutoDoOrcamentoPk() {}

    public ProdutoDoOrcamentoPk(Produto produto, Orcamento orcamento) {
        this.produto = produto;
        this.orcamento = orcamento;
    }

    public ProdutoDoOrcamentoPk(Produto produto) {
        this.produto = produto;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
