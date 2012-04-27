package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Associado;
import com.cooper.model.repository.AssociadoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class AssociadoRepositoryHibernate extends LookupRepositoryHibernate<Associado, String> implements AssociadoRepository {
    public AssociadoRepositoryHibernate() {
        super(Associado.class);
    }

    public boolean associadoWithCpfCnpjExists(Associado associado) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria(getPersistentClass());
            select.add( Restrictions.eq("codigo", associado.getCodigo()));
            select.add( Restrictions.eq("cpfcnpj", associado.getCpfcnpj()));

            Number count = (Number) select.setProjection(Projections.rowCount()).uniqueResult();

            return (count.intValue() > 0);
        } catch (HibernateException ex) {
            throw new RepositoryException(ex);
        } catch (Exception ex) {
            throw new RepositoryException(ex);
        }
    }
}
