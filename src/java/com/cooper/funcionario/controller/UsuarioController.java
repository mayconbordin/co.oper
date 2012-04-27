package com.cooper.funcionario.controller;

import com.cooper.model.entity.Usuario;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;

public class UsuarioController extends StrutsController {
    private UsuarioService usuarioService;

    // Actions =================================================================
    public String getUsuarioInfo() {
        try {
            String id = (String) request.getParameter("id");
            Usuario usuario = usuarioService.get(id);
            request.setAttribute("usuario", usuario);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String trocarSenha() {
        try {
            String id = (String) request.getParameter("id");
            String senha = (String) request.getParameter("senha");

            usuarioService.updateUsuario(id, senha);
            request.setAttribute("opStatus", 0);
            return SUCCESS;
        } catch (ValidatorException ex) {
            errorHandler(ex);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        request.setAttribute("opStatus", 1);
        return SUCCESS;
    }

    // Getters and Setters =====================================================
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
