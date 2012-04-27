package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

@Indexed
public class Produto implements Serializable {
    @DocumentId
    private int codigo;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private String descricao;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private double preco;

    @NotNull
    @XSSFilter
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @NotNull
    @XSSFilter
    @Length(max = 500)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    @XSSFilter
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}