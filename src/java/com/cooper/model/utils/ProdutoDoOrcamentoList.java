package com.cooper.model.utils;

import com.cooper.model.entity.ProdutoDoOrcamento;
import java.util.List;

/**
 *
 * @author maycon
 */
public interface ProdutoDoOrcamentoList extends List<ProdutoDoOrcamento> {
    public boolean containsProduto(int id);
    public boolean removeWithProduto(int id);
}
