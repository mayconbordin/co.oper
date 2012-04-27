package com.cooper.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Acesso implements Serializable {
    private AcessoPk primaryKey;
    private String pagina;
    private String codAssociado;

    public Acesso() {}

    public Acesso(Date data, String ip, String pagina, String codAssociado) {
        this.primaryKey = new AcessoPk(data, ip);
        this.pagina = pagina;
        this.codAssociado = codAssociado;
    }

    public String getCodAssociado() {
        return codAssociado;
    }

    public void setCodAssociado(String codAssociado) {
        this.codAssociado = codAssociado;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public AcessoPk getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AcessoPk primaryKey) {
        this.primaryKey = primaryKey;
    }
}
