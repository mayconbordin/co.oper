package com.cooper.model.utils;

import com.cooper.model.entity.ProdutoDoOrcamento;
import java.util.ArrayList;

/**
 *
 * @author maycon
 */
public class ProdutoDoOrcamentoArrayList 
        extends ArrayList<ProdutoDoOrcamento>
        implements ProdutoDoOrcamentoList {

    public boolean containsProduto(int id) {
        for (ProdutoDoOrcamento pdo : this) {
            if (pdo.getPrimaryKey().getProduto().getCodigo() == id) {
                return true;
            }
        }

        return false;
    }

    public boolean removeWithProduto(int id) {
        for (ProdutoDoOrcamento pdo : this) {
            if (pdo.getPrimaryKey().getProduto().getCodigo() == id) {
                return remove(pdo);
            }
        }

        return false;
    }
    
}
