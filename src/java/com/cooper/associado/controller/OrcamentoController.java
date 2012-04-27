package com.cooper.associado.controller;

import com.strutstool.validator.ValidatorException;
import java.util.List;
import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.service.OrcamentoService;
import com.cooper.model.service.ProdutoDoOrcamentoService;
import com.strutstool.struts.StrutsController;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.SessionStruts;

public class OrcamentoController extends StrutsController {

    private OrcamentoService orcamentoService;
    private ProdutoDoOrcamentoService produtoDoOrcamentoService;

    private List<Orcamento> orcamentoList;
    private List<ProdutoDoOrcamento> produtoDoOrcamentoList;

    private Orcamento orcamento;

    private Integer orcamentoId;
    private Integer produtoId;
    private Integer qtde;

    // Actions =================================================================
    
    public String index() {
        try {
            orcamentoList = getOrcamentoService().findAllByUsuario(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String detalhes() {
        try {
            orcamento = getOrcamentoService().get(orcamentoId);
            produtoDoOrcamentoList = getProdutoDoOrcamentoService().findAllByOrcamento(orcamentoId);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String detalhesAtual() {
        try {
            orcamento = getOrcamentoService().get(orcamentoId);
            produtoDoOrcamentoList = getProdutoDoOrcamentoService().findAllByOrcamento(orcamentoId);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String orcamentoConfirmDialog() {
        request.setAttribute("orcamento", getOrcamentoService().getProdutos());
        return SUCCESS;
    }

    public String orcamentoDialog() {
        request.setAttribute("orcamento", getOrcamentoService().getProdutos());
        return SUCCESS;
    }

    public String orcamentoBox() {
        request.setAttribute("produtosCount", getOrcamentoService().getProdutosCount());
        return SUCCESS;
    }

    public void addProduto() {
        try {
            getOrcamentoService().addProduto(produtoId, qtde);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
    }

    public void removeProduto() {
        getOrcamentoService().removeProduto(produtoId);
    }

    public void saveOrcamento() {
        try {
            getOrcamentoService().saveOrcamento(getUserAuth().getName());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }
    }

    public void removeOrcamento() {
        getOrcamentoService().removeOrcamento();
    }

    // Getters and Setters =====================================================
    
    public OrcamentoService getOrcamentoService() {
        if (orcamentoService.getSession() == null) {
            orcamentoService.setSession(new SessionStruts(session));
        }
        return orcamentoService;
    }

    public void setOrcamentoService(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Integer getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(Integer orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    public List<ProdutoDoOrcamento> getProdutoDoOrcamentoList() {
        return produtoDoOrcamentoList;
    }

    public void setProdutoDoOrcamentoList(List<ProdutoDoOrcamento> produtoDoOrcamentoList) {
        this.produtoDoOrcamentoList = produtoDoOrcamentoList;
    }

    public ProdutoDoOrcamentoService getProdutoDoOrcamentoService() {
        return produtoDoOrcamentoService;
    }

    public void setProdutoDoOrcamentoService(ProdutoDoOrcamentoService produtoDoOrcamentoService) {
        this.produtoDoOrcamentoService = produtoDoOrcamentoService;
    }
}
