package com.cooper.funcionario.controller;

import com.cooper.model.entity.Associado;
import com.cooper.model.service.AssociadoService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class AssociadoController extends StrutsController {
    private AssociadoService associadoService;
    private List<Associado> associadoList;

    // Actions =================================================================

    public String index() {
        try {
            associadoList = associadoService.getAll();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String criarConta() {
        try {
            String codigo   = (String) request.getParameter("codigo");
            String email    = (String) request.getParameter("email");
            String pass     = (String) request.getParameter("pass");

            associadoService.criarConta(codigo, email, pass);
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
    public AssociadoService getAssociadoService() {
        return associadoService;
    }

    public void setAssociadoService(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    public List<Associado> getAssociadoList() {
        return associadoList;
    }

    public void setAssociadoList(List<Associado> associadoList) {
        this.associadoList = associadoList;
    }
}
