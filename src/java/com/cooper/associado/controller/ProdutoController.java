package com.cooper.associado.controller;

import com.cooper.model.entity.Produto;
import com.cooper.model.service.OrcamentoService;
import com.cooper.model.service.ProdutoNaLojaService;
import com.cooper.model.service.ProdutoService;
import com.cooper.model.utils.ProdutoNaLojaArrayList;
import com.cooper.model.utils.ProdutoNaLojaList;
import com.strutstool.struts.StrutsController;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.SessionStruts;
import java.util.List;

public class ProdutoController extends StrutsController {
    private ProdutoService produtoService;
    private OrcamentoService orcamentoService;
    private ProdutoNaLojaService produtoNaLojaService;
    
    private List<Produto> produtoList;
    private ProdutoNaLojaList produtoNaLojaList;
    private String searchInput;

    // Actions =================================================================
    
    public String index() {
        try {
            if (searchInput == null || searchInput.isEmpty()) {
                produtoList = getProdutoService().findAll();
            } else {
                produtoList = getProdutoService().searchProdutos(searchInput);
            }
            request.setAttribute("orcamento", getOrcamentoService().getProdutos());

            produtoNaLojaList = new ProdutoNaLojaArrayList();
            produtoNaLojaList.addAll(getProdutoNaLojaService().findAll());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    // Getters and Setters =====================================================
    
    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public OrcamentoService getOrcamentoService() {
        if (orcamentoService.getSession() == null) {
            orcamentoService.setSession(new SessionStruts(session));
        }
        return orcamentoService;
    }

    public void setOrcamentoService(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public ProdutoNaLojaList getProdutoNaLojaList() {
        return produtoNaLojaList;
    }

    public void setProdutoNaLojaList(ProdutoNaLojaList produtoNaLojaList) {
        this.produtoNaLojaList = produtoNaLojaList;
    }

    public ProdutoNaLojaService getProdutoNaLojaService() {
        return produtoNaLojaService;
    }

    public void setProdutoNaLojaService(ProdutoNaLojaService produtoNaLojaService) {
        this.produtoNaLojaService = produtoNaLojaService;
    }

}
