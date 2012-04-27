package com.cooper.model.entity;

import com.strutstool.validator.constraints.NotNull;
import com.strutstool.validator.constraints.XSSFilter;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

public class Usuario implements Serializable {
    public static final int NIVEL_ASSOCIADO = 1;
    public static final int NIVEL_TECNICO = 2;
    public static final int NIVEL_FUNCIONARIO = 5;

    public static final String STATUS_ATIVO = "ativo";
    public static final String STATUS_INATIVO = "inativo";
    
    private String codigo;
    private String senha;
    private String email;
    private String status;
    private Date ultimoAcesso;
    private Date criadoEm;
    private int nivel;

    public Usuario() {}

    public Usuario(String codigo) {
        this.codigo = codigo;
    }

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
    @Length(max = 64)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @XSSFilter
    @Length(max = 80)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    @NotNull
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public static int getNivelUsuario(String nivelStr) {
        if (nivelStr.equals("NIVEL_ASSOCIADO")) {
            return NIVEL_ASSOCIADO;
        }

        if (nivelStr.equals("NIVEL_FUNCIONARIO")) {
            return NIVEL_FUNCIONARIO;
        }

        return 0;
    }
}