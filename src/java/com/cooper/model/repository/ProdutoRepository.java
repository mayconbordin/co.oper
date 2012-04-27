package com.cooper.model.repository;

import com.cooper.model.entity.Produto;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface ProdutoRepository extends LookupRepository<Produto, Integer>  {
    public List<Produto> searchProdutos(String searchString) throws RepositoryException;
}
