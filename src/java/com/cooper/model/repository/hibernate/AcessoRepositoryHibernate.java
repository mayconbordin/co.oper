package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Acesso;
import com.cooper.model.entity.AcessoPk;
import com.cooper.model.repository.AcessoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

public class AcessoRepositoryHibernate extends LookupRepositoryHibernate<Acesso, AcessoPk> implements AcessoRepository {
    public AcessoRepositoryHibernate() {
        super(Acesso.class);
    }

    public List<Object[]> getAcessosBetweenDates(Date startDate, Date endDate) throws RepositoryException {
        try {
            String sql = "select to_char(data, 'YYYY-MM-DD') as data, count(*) as total from acesso "
                       + "where data >= :startDate and data <= :endDate "
                       + "group by to_char(data, 'YYYY-MM-DD')";

            Query query = getSession().createSQLQuery(sql)
                .addScalar("data", StandardBasicTypes.DATE)
                .addScalar("total", StandardBasicTypes.INTEGER)
                .setDate("startDate", startDate)
                .setDate("endDate", endDate);

            return query.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public Integer getTotalAcessosByDate(Date start, Date end) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Acesso.class );
            select.add(Restrictions.ge("primaryKey.data", start));
            select.add(Restrictions.le("primaryKey.data", end));

            Number count = (Number) select.setProjection(Projections.rowCount()).uniqueResult();

            return count.intValue();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
