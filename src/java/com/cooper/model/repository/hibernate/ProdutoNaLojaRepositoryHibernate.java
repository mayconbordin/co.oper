package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.ProdutoNaLoja;
import com.cooper.model.entity.ProdutoNaLojaPk;
import com.cooper.model.repository.ProdutoNaLojaRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;

public class ProdutoNaLojaRepositoryHibernate extends LookupRepositoryHibernate<ProdutoNaLoja, ProdutoNaLojaPk> implements ProdutoNaLojaRepository {
    public ProdutoNaLojaRepositoryHibernate() {
        super(ProdutoNaLoja.class);
    }
}
