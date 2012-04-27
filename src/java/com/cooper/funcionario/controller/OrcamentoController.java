
package com.cooper.funcionario.controller;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.service.OrcamentoService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;
import java.util.List;
import java.util.Set;

public class OrcamentoController extends StrutsController {
    private OrcamentoService orcamentoService;
    private List<Orcamento> orcamentoList;

    private Set<ProdutoDoOrcamento> produtoDoOrcamentoList;

    private int orcamentoId;
    private String comentario;
    private double total;

    // Actions =================================================================

    public String index() {
        try {
            orcamentoList = getOrcamentoService().findAllOrderByStatusAndData();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public void saveRespostaOrcamento() {
        try {
            getOrcamentoService().saveRespostaOrcamento(orcamentoId, comentario, total);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }
    }

    public String mostraOrcamentosPendentes() {
        try {
            request.setAttribute("total", getOrcamentoService().getTotalOrcamentosPendentes());
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String mostraProdutosOrcamento() {
        try {
            Orcamento orcamento = getOrcamentoService().get(orcamentoId);
            produtoDoOrcamentoList = orcamento.getProdutos();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    // Getters and Setters =====================================================

    public OrcamentoService getOrcamentoService() {
        return orcamentoService;
    }

    public void setOrcamentoService(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(int orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<ProdutoDoOrcamento> getProdutoDoOrcamentoList() {
        return produtoDoOrcamentoList;
    }

    public void setProdutoDoOrcamentoList(Set<ProdutoDoOrcamento> produtoDoOrcamentoList) {
        this.produtoDoOrcamentoList = produtoDoOrcamentoList;
    }
}
