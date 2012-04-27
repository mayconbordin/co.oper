package com.cooper.model.service.impl;

import com.cooper.model.repository.AssociadoRepository;
import com.cooper.model.repository.hibernate.AssociadoRepositoryHibernate;
import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class AssociadoServiceImpl implements AssociadoService {
    private AssociadoRepository associadoRepository;
    private UsuarioService usuarioService;
    
    // Implemented interface methods ===========================================

    public Associado get(String id) throws RepositoryException {
        return getAssociadoRepository().get(id);
    }

    public Associado load(String id) throws RepositoryException {
        return getAssociadoRepository().load(id);
    }

    public List<Associado> getAll() throws RepositoryException {
        return getAssociadoRepository().getAll();
    }

    public void criarConta(String codigo, String email, String pass) throws RepositoryException, ValidatorException {
        Usuario usuario = getUsuarioService().get(codigo);

        if (usuario != null) {
            throw new RepositoryException("Associado já possui conta.");
        }

        getUsuarioService().addUsuario(codigo, email, pass, Usuario.NIVEL_ASSOCIADO);

        Associado associado = getAssociadoRepository().get(codigo);
        associado.setTemConta(true);
        getAssociadoRepository().save(associado);
    }

    public boolean associadoWithCpfCnpjExists(String codigo, String cpfCnpj) throws RepositoryException {
        Associado associado = new Associado();
        associado.setCodigo(codigo);
        associado.setCpfcnpj(cpfCnpj);

        return getAssociadoRepository().associadoWithCpfCnpjExists(associado);
    }

    public void setTemConta(String codigo) throws RepositoryException {
        Associado associado = getAssociadoRepository().get(codigo);
        associado.setTemConta(true);
        getAssociadoRepository().save(associado);
    }

    // Getters and Setters =====================================================

    protected AssociadoRepository getAssociadoRepository() {
        if (associadoRepository == null) {
            associadoRepository = new AssociadoRepositoryHibernate();
        }

        return associadoRepository;
    }

    public UsuarioService getUsuarioService() {
        if (usuarioService == null) {
            usuarioService = new UsuarioServiceImpl();
        }
        return usuarioService;
    }
}
