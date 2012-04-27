package com.cooper.model.utils;

import com.cooper.model.entity.ProdutoNaLoja;
import java.util.ArrayList;

public class ProdutoNaLojaArrayList
        extends ArrayList<ProdutoNaLoja>
        implements ProdutoNaLojaList  {

    public double getQtdeTotalProduto(int codProduto) {
        double total = 0;
        for (ProdutoNaLoja pnl : this) {
            if (pnl.getPrimaryKey().getProduto().getCodigo() == codProduto) {
                total += pnl.getQuantidade();
            }
        }

        return total;
    }

}
