package com.cooper.associado.controller;

import com.cooper.model.entity.Titulo;
import com.cooper.model.service.TituloService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import java.util.List;

public class TituloController extends StrutsController {
    
    private TituloService tituloService;
    private List<Titulo> tituloList;
    private double total;

    // Actions =================================================================
    
    public String index() {
        try {
            tituloList = getTituloService().findAllByUsuario(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String mostraTotal() {
        try {
            total = getTituloService().getTotalTitulosByUsuario(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    // Getters and Setters =====================================================

    public TituloService getTituloService() {
        return tituloService;
    }

    public void setTituloService(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    public List<Titulo> getTituloList() {
        return tituloList;
    }

    public void setTituloList(List<Titulo> tituloList) {
        this.tituloList = tituloList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
