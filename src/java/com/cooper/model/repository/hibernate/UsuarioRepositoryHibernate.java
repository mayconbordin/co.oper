package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.UsuarioRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import org.hibernate.HibernateException;

public class UsuarioRepositoryHibernate extends LookupRepositoryHibernate<Usuario, String> implements UsuarioRepository {
    public UsuarioRepositoryHibernate() {
        super(Usuario.class);
    }

    @Override
    public Usuario get(String id) throws RepositoryException {
        try {
            Usuario entity = (Usuario) getSession().get(getPersistentClass(), id);
            if (entity != null) getSession().refresh(entity);
            return entity;
        } catch (HibernateException ex) {
            throw new RepositoryException(ex);
        } catch (Exception ex) {
            throw new RepositoryException(ex);
        }
    }
}
