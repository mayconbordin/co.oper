package com.cooper.model.service;

import com.cooper.model.entity.Produto;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface ProdutoService {
    public Produto load(Integer id) throws RepositoryException;
    public List<Produto> findAll() throws RepositoryException;
    public List<Produto> searchProdutos(String searchString) throws RepositoryException;
}
