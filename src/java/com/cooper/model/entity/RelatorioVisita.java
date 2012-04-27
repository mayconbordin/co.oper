package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

public class RelatorioVisita implements Serializable {
    private int id;
    private Date data;
    private String descricao;
    private Associado associado;
    private Tecnico tecnico;
    private String comentario;
    private Integer nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Length(max = 1000)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    @NotNull
    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    @XSSFilter
    @Length(max = 1000)
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
    
}