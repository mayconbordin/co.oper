package com.cooper.model.service;

import com.cooper.model.entity.ProdutoDoOrcamento;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public interface ProdutoDoOrcamentoService {
    public void saveAll(List<ProdutoDoOrcamento> produtoDoOrcamentoList) throws RepositoryException, ValidatorException;
    public List<ProdutoDoOrcamento> findAllByOrcamento(Integer id) throws RepositoryException;
}
