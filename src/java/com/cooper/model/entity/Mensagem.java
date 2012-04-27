package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

public class Mensagem implements Serializable {
    private int id;
    private String conteudo;
    private Date data;
    private boolean fonteLeu;
    private boolean destinoLeu;
    private String enviadoPor;
    private String enviadoPara;
    private int enviadoPorFonte;
    private Associado associado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @XSSFilter
    @Length(max = 1000)
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @NotNull
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEnviadoPor() {
        return enviadoPor;
    }

    public void setEnviadoPor(String enviadoPor) {
        this.enviadoPor = enviadoPor;
    }

    public String getEnviadoPara() {
        return enviadoPara;
    }

    public void setEnviadoPara(String enviadoPara) {
        this.enviadoPara = enviadoPara;
    }

    public int getEnviadoPorFonte() {
        return enviadoPorFonte;
    }

    public void setEnviadoPorFonte(int enviadoPorFonte) {
        this.enviadoPorFonte = enviadoPorFonte;
    }

    public boolean isDestinoLeu() {
        return destinoLeu;
    }

    public void setDestinoLeu(boolean destinoLeu) {
        this.destinoLeu = destinoLeu;
    }

    public boolean isFonteLeu() {
        return fonteLeu;
    }

    public void setFonteLeu(boolean fonteLeu) {
        this.fonteLeu = fonteLeu;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }
}