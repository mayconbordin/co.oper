package com.cooper.funcionario.controller;

import com.cooper.model.entity.Tecnico;
import com.cooper.model.service.TecnicoService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;

public class TecnicoController extends StrutsController {
    private TecnicoService tecnicoService;

    // Actions =================================================================
    public String index() {
        try {
            request.setAttribute("tecnicoList", tecnicoService.getAll());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String addTecnico() {
        try {
            String nome     = (String) request.getParameter("nome");
            String email    = (String) request.getParameter("email");
            String pass     = (String) request.getParameter("pass");

            Tecnico tecnico = tecnicoService.newTecnico(nome, pass, email);
            request.setAttribute("tecnico", tecnico);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    // Getters and Setters =====================================================
    public TecnicoService getTecnicoService() {
        return tecnicoService;
    }

    public void setTecnicoService(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }
}
