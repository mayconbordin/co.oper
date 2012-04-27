package com.cooper.model.service;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.utils.ProdutoDoOrcamentoList;
import com.strutstool.repository.RepositoryException;
import com.strutstool.session.Session;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public interface OrcamentoService {
    // Business functionality
    public ProdutoDoOrcamentoList getProdutos();
    public int getProdutosCount();
    public void addProduto(int produtoId, int qtde) throws RepositoryException;
    public void removeProduto(int produtoId);
    public void saveOrcamento(String usuarioId) throws RepositoryException, ValidatorException;
    public void removeOrcamento();
    public void saveRespostaOrcamento(int orcamentoId, String comentario, double total) throws RepositoryException, ValidatorException;

    public Integer getTotalOrcamentosPendentes() throws RepositoryException;

    // Setter interface
    public void setSession(Session session);
    public Session getSession();

    // Commom functionality
    public List<Orcamento> findAllByUsuario(String id) throws RepositoryException;
    public List<Orcamento> findAllOrderByStatusAndData() throws RepositoryException;
    public Orcamento get(Integer id) throws RepositoryException;
}
