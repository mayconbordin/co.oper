package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.entity.ProdutoDoOrcamentoPk;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.ProdutoDoOrcamentoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

public class ProdutoDoOrcamentoRepositoryHibernate extends LookupRepositoryHibernate<ProdutoDoOrcamento, ProdutoDoOrcamentoPk> implements ProdutoDoOrcamentoRepository {
    public ProdutoDoOrcamentoRepositoryHibernate() {
        super(ProdutoDoOrcamento.class);
    }

    public List<ProdutoDoOrcamento> findAllByOrcamento(Orcamento orcamento) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( ProdutoDoOrcamento.class );
            select.add(Restrictions.eq("primaryKey.orcamento.id", orcamento.getId()));

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
