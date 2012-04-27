package com.cooper.model.service;

import com.cooper.model.entity.ProdutoNaLoja;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface ProdutoNaLojaService {
    public List<ProdutoNaLoja> findAll() throws RepositoryException;
}
