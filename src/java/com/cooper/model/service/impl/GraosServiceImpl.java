package com.cooper.model.service.impl;

import com.cooper.model.repository.GraosRepository;
import com.cooper.model.repository.hibernate.GraosRepositoryHibernate;
import com.cooper.model.entity.Graos;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.GraosService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public class GraosServiceImpl implements GraosService {
    private GraosRepository graosRepository;
    private UsuarioService usuarioService;

    public GraosServiceImpl() {}

    public GraosServiceImpl(GraosRepository graosRepository) {
        this.graosRepository = graosRepository;
    }
    
    // Implemented interface methods ===========================================

    public List<Graos> findAllByUsuario(String id) throws RepositoryException {
        Usuario usuario = getUsuarioService().get(id);
        return getGraosRepository().findAllByUsuario(usuario);
    }

    // Getters and Setters =====================================================

    protected GraosRepository getGraosRepository() {
        if (graosRepository == null) {
            graosRepository = new GraosRepositoryHibernate();
        }

        return graosRepository;
    }
    
    protected UsuarioService getUsuarioService() {
        if (usuarioService == null) {
            usuarioService = new UsuarioServiceImpl();
        }
        return usuarioService;
    }
}
