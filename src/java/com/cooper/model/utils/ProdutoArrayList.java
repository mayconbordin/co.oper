package com.cooper.model.utils;

import com.cooper.model.entity.Produto;
import java.util.ArrayList;

/**
 *
 * @author maycon
 */
public class ProdutoArrayList extends ArrayList<Produto> implements ProdutoList {
    public ProdutoArrayList() {
        super();
    }

    public boolean containsProduto(int id) {
        for (Produto p : this) {
            if (p.getCodigo() == id) {
                return true;
            }
        }

        return false;
    }
}
