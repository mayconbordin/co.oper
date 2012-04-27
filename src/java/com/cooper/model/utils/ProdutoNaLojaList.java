package com.cooper.model.utils;

import com.cooper.model.entity.ProdutoNaLoja;
import java.util.List;

public interface ProdutoNaLojaList extends List<ProdutoNaLoja> {
    public double getQtdeTotalProduto(int codProduto);
}
