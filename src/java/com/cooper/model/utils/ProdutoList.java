package com.cooper.model.utils;

import com.cooper.model.entity.Produto;
import java.util.List;

/**
 *
 * @author maycon
 */
public interface ProdutoList extends List<Produto> {
    public boolean containsProduto(int id);
}
