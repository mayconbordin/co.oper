package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Titulo;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.TituloRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TituloRepositoryHibernate extends LookupRepositoryHibernate<Titulo, Integer> implements TituloRepository {
    public TituloRepositoryHibernate() {
        super(Titulo.class);
    }

    public List<Titulo> findAllByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Titulo.class );
            select.add(Restrictions.eq("associado.codigo",usuario.getCodigo()));
            select.addOrder(Order.desc("dataCriacao"));
            
            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public double getTotalTitulosByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Titulo.class );
            select.add(Restrictions.eq("associado.codigo",usuario.getCodigo()));
            select.setProjection(Projections.sum("valor"));
            Double total = (Double) select.uniqueResult();
            
            return total;
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
