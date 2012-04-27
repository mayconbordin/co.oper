package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.RelatorioVisita;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.RelatorioVisitaRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class RelatorioVisitaRepositoryHibernate extends LookupRepositoryHibernate<RelatorioVisita, Integer> implements RelatorioVisitaRepository {
    public RelatorioVisitaRepositoryHibernate() {
        super(RelatorioVisita.class);
    }

    public List<RelatorioVisita> findAllByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( RelatorioVisita.class );
            select.add(Restrictions.eq("associado.codigo", usuario.getCodigo()));
            select.addOrder(Order.desc("data"));

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public List<RelatorioVisita> findAllByTecnico(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( RelatorioVisita.class );
            select.add(Restrictions.eq("tecnico.id", usuario.getCodigo()));
            select.addOrder(Order.desc("data"));

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
