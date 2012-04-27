package com.cooper.associado.controller;

import java.util.List;
import com.cooper.model.entity.Graos;
import com.cooper.model.service.GraosService;
import com.strutstool.struts.StrutsController;
import com.strutstool.repository.RepositoryException;

public class GraosController extends StrutsController {

    private GraosService graosService;
    private List<Graos> graosList;

    // Actions =================================================================

    public String index() {
        try {
            graosList = getGraosService().findAllByUsuario(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    // Getters and Setters =====================================================
    
    public GraosService getGraosService() {
        return graosService;
    }

    public void setGraosService(GraosService graosService) {
        this.graosService = graosService;
    }

    public List<Graos> getGraosList() {
        return graosList;
    }

    public void setGraosList(List<Graos> graosList) {
        this.graosList = graosList;
    }
}
