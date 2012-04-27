package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Mensagem;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.MensagemRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class MensagemRepositoryHibernate extends LookupRepositoryHibernate<Mensagem, Integer> implements MensagemRepository {
    public MensagemRepositoryHibernate() {
        super(Mensagem.class);
    }

    public List<Mensagem> findAllByUsuario(Usuario usuario, String filtroTipo, int resultSize) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Mensagem.class );

            if (filtroTipo.toLowerCase().equals("enviadas")) {
                select.add(Restrictions.and(
                    Restrictions.eq("enviadoPor",usuario.getCodigo()),
                    Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO)
                ));

            } else if (filtroTipo.toLowerCase().equals("recebidas")) {
                select.add(Restrictions.and(
                    Restrictions.eq("enviadoPara",usuario.getCodigo()),
                    Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                ));
            } else {
                select.add( Restrictions.or(
                        Restrictions.and(
                            Restrictions.eq("enviadoPor",usuario.getCodigo()),
                            Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO)
                        ),
                        Restrictions.and(
                            Restrictions.eq("enviadoPara",usuario.getCodigo()),
                            Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                        )
                ));
            }

            select.addOrder(Order.asc("destinoLeu"));
            select.addOrder(Order.desc("data"));

            if (resultSize != -1) {
                select.setMaxResults(resultSize);
            }

            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public int countNaoLidasByUsuario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria(getPersistentClass());
            select.add( Restrictions.or(
                    Restrictions.and(
                        Restrictions.eq("enviadoPor",usuario.getCodigo()),
                        Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO)
                    ),
                    Restrictions.and(
                        Restrictions.eq("enviadoPara",usuario.getCodigo()),
                        Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                    )
            ));
            
            select.add( Restrictions.eq("destinoLeu", false));

            Number count = (Number) select.setProjection(Projections.rowCount()).uniqueResult();

            return count.intValue();
        } catch (HibernateException ex) {
            throw new RepositoryException(ex);
        } catch (Exception ex) {
            throw new RepositoryException(ex);
        }
    }

    public List<Mensagem> findAllByFuncionario(Usuario usuario, String filtroTipo, int resultSize) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria( Mensagem.class );

            if (filtroTipo.toLowerCase().equals("enviadas")) {
                select.add(Restrictions.and(
                    Restrictions.eq("enviadoPor",usuario.getCodigo()),
                    Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                ));

            } else if (filtroTipo.toLowerCase().equals("recebidas")) {
                select.add(Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO));
            } else {
                select.add( Restrictions.or(
                        Restrictions.and(
                            Restrictions.eq("enviadoPor",usuario.getCodigo()),
                            Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                        ),
                        Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO)
                ));
            }

            select.addOrder(Order.asc("destinoLeu"));
            select.addOrder(Order.desc("data"));

            if (resultSize != -1) {
                select.setMaxResults(resultSize);
            }
            
            return select.list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    public int countNaoLidasByFuncionario(Usuario usuario) throws RepositoryException {
        try {
            Criteria select = getSession().createCriteria(getPersistentClass());
            select.add( Restrictions.or(
                    Restrictions.and(
                        Restrictions.eq("enviadoPor",usuario.getCodigo()),
                        Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_FUNCIONARIO)
                    ),
                    Restrictions.eq("enviadoPorFonte", Usuario.NIVEL_ASSOCIADO)
            ));

            select.add( Restrictions.eq("destinoLeu", false));

            Number count = (Number) select.setProjection(Projections.rowCount()).uniqueResult();

            return count.intValue();
        } catch (HibernateException ex) {
            throw new RepositoryException(ex);
        } catch (Exception ex) {
            throw new RepositoryException(ex);
        }
    }
}
