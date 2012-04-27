package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

@Indexed
public class Associado implements Serializable {
    @DocumentId
    private String codigo;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private String nome;

    @Field(index=Index.TOKENIZED,store=Store.YES)
    private String cpfcnpj;
    
    private Date dataCadastro;
    private boolean temConta;

    @NotNull
    @XSSFilter
    @Length(max = 20)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @NotNull
    @XSSFilter
    @Length(max = 120)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    @XSSFilter
    @Length(max = 18)
    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    @NotNull
    @XSSFilter
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isTemConta() {
        return temConta;
    }

    public void setTemConta(boolean temConta) {
        this.temConta = temConta;
    }
}