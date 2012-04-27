package com.cooper.tecnico.controller;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.RelatorioVisita;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.RelatorioVisitaService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class RelatorioVisitaController extends StrutsController {
    private RelatorioVisitaService relatorioVisitaService;
    private AssociadoService associadoService;

    private List<Associado> associadoList;
    private RelatorioVisita relatorioVisita;
    private String tecnicoId;

    public RelatorioVisitaController() {
        tecnicoId = getUserAuth().getName();
    }

    // Actions =================================================================
    public String index() {
        try {
            request.setAttribute(
                "relatorioVisitaList",
                relatorioVisitaService.findAllByTecnico(getUserAuth().getName())
            );

            associadoList = associadoService.getAll();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String salvarRelatorioVisita() {
        try {
            String id           = request.getParameter("id");
            String data         = request.getParameter("data");
            String descricao    = request.getParameter("descricao");
            String associado    = request.getParameter("associado");
            String tecnico      = request.getParameter("tecnico");

            relatorioVisita =
                relatorioVisitaService.saveRelatorioVisita(
                    Integer.parseInt(id),
                    data,
                    descricao,
                    associado,tecnico
                );
        } catch (ValidatorException ex) {
            errorHandler(ex);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }
    
    // Getters and Setters =====================================================
    public RelatorioVisitaService getRelatorioVisitaService() {
        return relatorioVisitaService;
    }

    public void setRelatorioVisitaService(RelatorioVisitaService relatorioVisitaService) {
        this.relatorioVisitaService = relatorioVisitaService;
    }

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

    public String getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(String tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public RelatorioVisita getRelatorioVisita() {
        return relatorioVisita;
    }

    public void setRelatorioVisita(RelatorioVisita relatorioVisita) {
        this.relatorioVisita = relatorioVisita;
    }
}
