package com.cooper.model.service.impl;

import com.cooper.model.repository.ProdutoNaLojaRepository;
import com.cooper.model.repository.hibernate.ProdutoNaLojaRepositoryHibernate;
import com.cooper.model.entity.ProdutoNaLoja;
import com.cooper.model.service.ProdutoNaLojaService;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public class ProdutoNaLojaServiceImpl implements ProdutoNaLojaService {
    private ProdutoNaLojaRepository produtoNaLojaRepository;

    public ProdutoNaLojaServiceImpl() {}

    public ProdutoNaLojaServiceImpl(ProdutoNaLojaRepository produtoNaLojaRepository) {
        this.produtoNaLojaRepository = produtoNaLojaRepository;
    }
    
    // Implemented interface methods ===========================================

    public List<ProdutoNaLoja> findAll() throws RepositoryException {
        return getProdutoNaLojaRepository().getAll();
    }

    // Getters and Setters =====================================================

    protected ProdutoNaLojaRepository getProdutoNaLojaRepository() {
        if (produtoNaLojaRepository == null) {
            produtoNaLojaRepository = new ProdutoNaLojaRepositoryHibernate();
        }

        return produtoNaLojaRepository;
    }
}
