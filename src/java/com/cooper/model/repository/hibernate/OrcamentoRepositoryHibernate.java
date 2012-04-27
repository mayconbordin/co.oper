package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.OrcamentoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class OrcamentoRepositoryHibernate extends LookupRepositoryHibernate<Orcamento, Integer> implements OrcamentoRepository {
    public OrcamentoRepositoryHibernate() {
        super(Orcamento.class);
    }

    public List<Orcamento> findAllByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Orcamento.class );
            select.add(Restrictions.eq("associado.codigo", usuario.getCodigo()));
            select.addOrder(Order.desc("data"));

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public Integer getTotalOrcamentosPendentes() throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Orcamento.class );
            select.add(Restrictions.eq("status", Orcamento.STATUS_PENDENTE));

            Number count = (Number) select.setProjection(Projections.rowCount()).uniqueResult();

            return count.intValue();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
