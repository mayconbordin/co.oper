package com.cooper.associado.controller;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;

public class ContaController extends StrutsController {
    private Associado associado;
    
    private String email;
    private String senha;

    private UsuarioService usuarioService;
    private AssociadoService associadoService;
	
    // Actions =================================================================
    public String index() {
        try {
            Usuario usuario = getUsuarioService().get(getUserAuth().getName());
            associado = getAssociadoService().get(getUserAuth().getName());

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

    public String accountBox() {
        return SUCCESS;
    }
    // Getters and Setters =====================================================

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public AssociadoService getAssociadoService() {
        return associadoService;
    }

    public void setAssociadoService(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

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
}
