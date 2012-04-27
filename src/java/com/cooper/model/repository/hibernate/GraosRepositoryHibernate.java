package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Graos;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.GraosRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class GraosRepositoryHibernate extends LookupRepositoryHibernate<Graos, Integer> implements GraosRepository {
    public GraosRepositoryHibernate() {
        super(Graos.class);
    }

    public List<Graos> findAllByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Graos.class );
            select.add(Restrictions.eq("associado.codigo",usuario.getCodigo()));
            select.addOrder(Order.desc("data"));

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
