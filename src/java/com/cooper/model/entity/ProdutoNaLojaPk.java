package com.cooper.model.entity;

import java.io.Serializable;

public class ProdutoNaLojaPk implements Serializable {
    private Loja loja;
    private Produto produto;

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
