package com.cooper.model.repository;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.entity.ProdutoDoOrcamentoPk;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface ProdutoDoOrcamentoRepository extends LookupRepository<ProdutoDoOrcamento, ProdutoDoOrcamentoPk>  {
    public List<ProdutoDoOrcamento> findAllByOrcamento(Orcamento orcamento) throws RepositoryException;
}
