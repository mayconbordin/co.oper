package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Loja;
import com.cooper.model.repository.LojaRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;

public class LojaRepositoryHibernate extends LookupRepositoryHibernate<Loja, Integer> implements LojaRepository {
    public LojaRepositoryHibernate() {
        super(Loja.class);
    }
}
