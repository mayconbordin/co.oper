package com.cooper.model.service.impl;

import com.cooper.model.entity.Tecnico;
import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.TecnicoRepository;
import com.cooper.model.repository.hibernate.TecnicoRepositoryHibernate;
import com.cooper.model.service.TecnicoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class TecnicoServiceImpl implements TecnicoService {
    private TecnicoRepository tecnicoRepository;
    private Validator tecnicoValidator;

    private UsuarioService usuarioService;
    
    // Implemented interface methods ===========================================
    public List<Tecnico> getAll() throws RepositoryException {
        return getTecnicoRepository().findAll("id", "asc");
    }

    public Tecnico get(String id) throws RepositoryException {
        return getTecnicoRepository().get(id);
    }

    public Tecnico newTecnico(String nome, String senha, String email) throws RepositoryException, ValidatorException {
        String id = Tecnico.ID_PREFIX + getTecnicoRepository().getNextSequence();
        
        Tecnico tecnico = new Tecnico();
        tecnico.setId(id);
        tecnico.setNome(nome);

        getTecnicoValidator().validate(tecnico);
        getTecnicoRepository().save(tecnico);

        getUsuarioService().addUsuario(id, email, senha, Usuario.NIVEL_TECNICO);

        return tecnico;
    }

    // Getters and Setters =====================================================
    protected TecnicoRepository getTecnicoRepository() {
        if (tecnicoRepository == null) {
            tecnicoRepository = new TecnicoRepositoryHibernate();
        }
        return tecnicoRepository;
    }

    protected UsuarioService getUsuarioService() {
        if (usuarioService == null) {
            usuarioService = new UsuarioServiceImpl();
        }
        return usuarioService;
    }

    protected Validator getTecnicoValidator() {
        if (tecnicoValidator == null) {
            tecnicoValidator = new Validator<Tecnico>();
        }
        return tecnicoValidator;
    }
}
