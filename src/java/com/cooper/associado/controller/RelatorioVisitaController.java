package com.cooper.associado.controller;

import com.strutstool.validator.ValidatorException;
import java.util.List;
import com.cooper.model.entity.RelatorioVisita;
import com.cooper.model.service.RelatorioVisitaService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;

public class RelatorioVisitaController extends StrutsController {
    private RelatorioVisitaService relatorioVisitaService;

    private List<RelatorioVisita> relatorioVisitaList;
    private RelatorioVisita relatorioVisita;
    private Integer relatorioVisitaId;

    private String comentario;
    private int nota;

    // Actions =================================================================
    
    public String index() {
        try {
            relatorioVisitaList = getRelatorioVisitaService().findAllByUsuario(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public void saveAvaliacao() {
        try {
            getRelatorioVisitaService().saveAvaliacao(relatorioVisitaId, comentario, nota);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }
    }

    // Getters and Setters =====================================================

    public RelatorioVisitaService getRelatorioVisitaService() {
        return relatorioVisitaService;
    }

    public void setRelatorioVisitaService(RelatorioVisitaService relatorioVisitaService) {
        this.relatorioVisitaService = relatorioVisitaService;
    }

    public RelatorioVisita getRelatorioVisita() {
        return relatorioVisita;
    }

    public void setRelatorioVisita(RelatorioVisita relatorioVisita) {
        this.relatorioVisita = relatorioVisita;
    }

    public Integer getRelatorioVisitaId() {
        return relatorioVisitaId;
    }

    public void setRelatorioVisitaId(Integer relatorioVisitaId) {
        this.relatorioVisitaId = relatorioVisitaId;
    }

    public List<RelatorioVisita> getRelatorioVisitaList() {
        return relatorioVisitaList;
    }

    public void setRelatorioVisitaList(List<RelatorioVisita> relatorioVisitaList) {
        this.relatorioVisitaList = relatorioVisitaList;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
