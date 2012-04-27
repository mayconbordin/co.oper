package com.cooper.funcionario.controller;

import com.cooper.model.service.AcessoService;
import com.strutstool.date.DateUtil;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AcessoController extends StrutsController {
    private AcessoService acessoService;
    private Map<Date, Integer> acessosList;
    private Date dataInicial;
    private Date dataFinal;

    // Actions =================================================================
    public String index() {
        try {
            if (dataFinal == null || dataInicial == null) {
                Calendar cal = Calendar.getInstance();
                dataFinal = cal.getTime();

                cal.add(Calendar.MONTH, -1);
                dataInicial = cal.getTime();
            }
            
            acessosList = acessoService.getAcessosBetweenDates(dataInicial, dataFinal);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String mostraAcessosHoje() {
        try {
            request.setAttribute("total", acessoService.getTotalAcessosHoje());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    // Getters and Setters =====================================================
    public AcessoService getAcessoService() {
        return acessoService;
    }

    public void setAcessoService(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    public Map<Date, Integer> getAcessosList() {
        return acessosList;
    }

    public void setAcessosList(Map<Date, Integer> acessosList) {
        this.acessosList = acessosList;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = DateUtil.formatDate((String) dataFinal);
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = DateUtil.formatDate((String) dataInicial);
    }
}
