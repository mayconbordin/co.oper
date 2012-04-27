package com.cooper.model.service.impl;

import com.cooper.model.repository.UsuarioRepository;
import com.cooper.model.repository.hibernate.UsuarioRepositoryHibernate;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.hash.SHA256;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.Calendar;

public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private Validator usuarioValidator;

    private AssociadoService associadoService;

    public UsuarioServiceImpl() {}

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    // Implemented interface methods ===========================================

    public void updateUsuario(String usuarioId, String email, String senha) throws RepositoryException, ValidatorException {
        Usuario usuario = getUsuarioRepository().get(usuarioId);
        
        if (!senha.isEmpty()) {
            usuario.setSenha(SHA256.calculate(senha));
        }
        
        if (!usuario.getEmail().equals(email)) {
            usuario.setEmail(email);
        }
        
        getUsuarioValidator().validate(usuario);
        getUsuarioRepository().save(usuario);
    }

    public void updateUsuario(String usuarioId, String senha) throws RepositoryException, ValidatorException {
        Usuario usuario = getUsuarioRepository().get(usuarioId);

        if (!senha.isEmpty()) {
            usuario.setSenha(SHA256.calculate(senha));
        }

        getUsuarioValidator().validate(usuario);
        getUsuarioRepository().save(usuario);
    }

    public Usuario get(String id) throws RepositoryException {
        return getUsuarioRepository().get(id);
    }

    public Usuario load(String id) throws RepositoryException {
        return getUsuarioRepository().load(id);
    }

    public void addUsuario(String id, String email, String senha, int nivel) throws RepositoryException, ValidatorException {
        addUsuario(id, email, senha, nivel, Usuario.STATUS_ATIVO);
    }

    public boolean cadastrarAssociado(String id, String email, String senha, String cpfCnpj) throws RepositoryException, ValidatorException {
        if (getAssociadoService().associadoWithCpfCnpjExists(id, cpfCnpj)) {
            addUsuario(id, email, senha, Usuario.NIVEL_ASSOCIADO, Usuario.STATUS_ATIVO);
            getAssociadoService().setTemConta(id);
            return true;
        }
        return false;
    }

    // Utility methods =========================================================
    private void addUsuario(String id, String email, String senha, int nivel, String status) throws RepositoryException, ValidatorException {
        Usuario usuario = getUsuarioRepository().get(id);

        if (usuario != null) {
            throw new RepositoryException("Usuário de código " + id + " já existe");
        }

        if (senha == null || senha.isEmpty()) {
            throw new RepositoryException("Senha não pode ser nula");
        }

        usuario = new Usuario();
        usuario.setCodigo(id);
        usuario.setSenha(SHA256.calculate(senha));
        usuario.setCriadoEm(Calendar.getInstance().getTime());
        usuario.setNivel(nivel);
        usuario.setStatus(status);

        if (email != null && !email.isEmpty()) {
            usuario.setEmail(email);
        }

        getUsuarioValidator().validate(usuario);
        getUsuarioRepository().save(usuario);
    }

    // Getters and Setters =====================================================

    protected UsuarioRepository getUsuarioRepository() {
        if (usuarioRepository == null) {
            usuarioRepository = new UsuarioRepositoryHibernate();
        }

        return usuarioRepository;
    }

    protected Validator getUsuarioValidator() {
        if (usuarioValidator == null) {
            usuarioValidator = new Validator<Usuario>();
        }
        return usuarioValidator;
    }

    protected AssociadoService getAssociadoService() {
        if (associadoService == null) {
            associadoService = new AssociadoServiceImpl();
        }
        return associadoService;
    }
}
