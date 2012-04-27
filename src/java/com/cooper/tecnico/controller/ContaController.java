package com.cooper.tecnico.controller;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Tecnico;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.TecnicoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;

public class ContaController extends StrutsController {
    private Tecnico tecnico;

    private String email;
    private String senha;

    private UsuarioService usuarioService;
    private TecnicoService tecnicoService;
    // Actions =================================================================
    public String index() {
        try {
            Usuario usuario = getUsuarioService().get(getUserAuth().getName());
            tecnico = getTecnicoService().get(getUserAuth().getName());

            email = usuario.getEmail();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        statusHandler();
        return SUCCESS;
    }

    public String save() {
        try {
            getUsuarioService().updateUsuario(getUserAuth().getName(), email, senha);
            return SUCCESS;
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }
        return ERROR;
    }

    // Getters and Setters =====================================================
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TecnicoService getTecnicoService() {
        return tecnicoService;
    }

    public void setTecnicoService(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}
