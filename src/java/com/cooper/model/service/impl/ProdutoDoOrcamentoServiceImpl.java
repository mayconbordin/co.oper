package com.cooper.model.service.impl;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.repository.ProdutoDoOrcamentoRepository;
import com.cooper.model.repository.hibernate.ProdutoDoOrcamentoRepositoryHibernate;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.service.OrcamentoService;
import com.cooper.model.service.ProdutoDoOrcamentoService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class ProdutoDoOrcamentoServiceImpl implements ProdutoDoOrcamentoService {
    private ProdutoDoOrcamentoRepository produtoDoOrcamentoRepository;
    private Validator produtoDoOrcamentoValidator;

    private OrcamentoService orcamentoService;

    public ProdutoDoOrcamentoServiceImpl() {}

    public ProdutoDoOrcamentoServiceImpl(ProdutoDoOrcamentoRepository produtoDoOrcamentoRepository) {
        this.produtoDoOrcamentoRepository = produtoDoOrcamentoRepository;
    }
    
    // Implemented interface methods ===========================================
    
    public void saveAll(List<ProdutoDoOrcamento> produtoDoOrcamentoList) throws RepositoryException, ValidatorException {
        for (ProdutoDoOrcamento pdo : produtoDoOrcamentoList) {
            getProdutoDoOrcamentoValidator().validate(pdo);
        }
        getProdutoDoOrcamentoRepository().saveAll(produtoDoOrcamentoList);
    }

    public List<ProdutoDoOrcamento> findAllByOrcamento(Integer id) throws RepositoryException {
        Orcamento orcamento = getOrcamentoService().get(id);
        return getProdutoDoOrcamentoRepository().findAllByOrcamento(orcamento);
    }

    // Getters and Setters =====================================================

    protected ProdutoDoOrcamentoRepository getProdutoDoOrcamentoRepository() {
        if (produtoDoOrcamentoRepository == null) {
            produtoDoOrcamentoRepository = new ProdutoDoOrcamentoRepositoryHibernate();
        }

        return produtoDoOrcamentoRepository;
    }

    protected Validator getProdutoDoOrcamentoValidator() {
        if (produtoDoOrcamentoValidator == null) {
            produtoDoOrcamentoValidator = new Validator<ProdutoDoOrcamento>();
        }
        return produtoDoOrcamentoValidator;
    }

    protected OrcamentoService getOrcamentoService() {
        if (orcamentoService == null) {
            orcamentoService = new OrcamentoServiceImpl();
        }
        return orcamentoService;
    }
}
