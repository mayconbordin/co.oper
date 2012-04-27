package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Tecnico;
import com.cooper.model.repository.TecnicoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.math.BigDecimal;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class TecnicoRepositoryHibernate extends LookupRepositoryHibernate<Tecnico, String> implements TecnicoRepository {
    public TecnicoRepositoryHibernate() {
        super(Tecnico.class);
    }

    public BigDecimal getNextSequence() throws RepositoryException {
        try {
            Query query = getSession().createSQLQuery("select tecnico_id_seq.nextval from dual");

            BigDecimal seq = (BigDecimal) query.uniqueResult();
            return seq;
        } catch (HibernateException ex) {
            throw new RepositoryException(ex);
        } catch (Exception ex) {
            throw new RepositoryException(ex);
        }
    }
}
