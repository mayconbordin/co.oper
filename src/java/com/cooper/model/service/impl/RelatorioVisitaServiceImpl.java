package com.cooper.model.service.impl;

import com.cooper.model.entity.Associado;
import com.cooper.model.repository.RelatorioVisitaRepository;
import com.cooper.model.repository.hibernate.RelatorioVisitaRepositoryHibernate;
import com.cooper.model.entity.RelatorioVisita;
import com.cooper.model.entity.Tecnico;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.RelatorioVisitaService;
import com.cooper.model.service.TecnicoService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.date.DateUtil;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class RelatorioVisitaServiceImpl implements RelatorioVisitaService {
    private RelatorioVisitaRepository relatorioVisitaRepository;
    private Validator relatorioVisitaValidator;

    private UsuarioService usuarioService;
    private AssociadoService associadoService;
    private TecnicoService tecnicoService;

    public RelatorioVisitaServiceImpl() {}

    public RelatorioVisitaServiceImpl(RelatorioVisitaRepository relatorioVisitaRepository) {
        this.relatorioVisitaRepository = relatorioVisitaRepository;
    }
    
    // Implemented interface methods ===========================================

    public void saveAvaliacao(Integer relatorioVisitaId, String comentario, int nota) throws RepositoryException, ValidatorException {
        RelatorioVisita relatorioVisita = getRelatorioVisitaRepository().load(relatorioVisitaId);
        relatorioVisita.setComentario(comentario);
        relatorioVisita.setNota(nota);

        getRelatorioVisitaValidator().validate(relatorioVisita);
        getRelatorioVisitaRepository().save(relatorioVisita);
    }

    public RelatorioVisita saveRelatorioVisita(int id, String data, String descricao, String associadoId, String tecnicoId) throws RepositoryException, ValidatorException {
        RelatorioVisita relatorioVisita = null;
        if (id == -1) {
            relatorioVisita = new RelatorioVisita();
        } else {
            relatorioVisita = getRelatorioVisitaRepository().get(id);
        }

        Associado associado = getAssociadoService().get(associadoId);
        Tecnico tecnico = getTecnicoService().get(tecnicoId);

        relatorioVisita.setAssociado(associado);
        relatorioVisita.setTecnico(tecnico);
        relatorioVisita.setDescricao(descricao);
        relatorioVisita.setData(DateUtil.formatDate(data));

        getRelatorioVisitaValidator().validate(relatorioVisita);
        getRelatorioVisitaRepository().save(relatorioVisita);

        return relatorioVisita;
    }

    public List<RelatorioVisita> findAllByUsuario(String id) throws RepositoryException {
        Usuario usuario = getUsuarioService().get(id);
        return getRelatorioVisitaRepository().findAllByUsuario(usuario);
    }

    public List<RelatorioVisita> findAllByTecnico(String id) throws RepositoryException {
        Usuario usuario = getUsuarioService().get(id);
        return getRelatorioVisitaRepository().findAllByTecnico(usuario);
    }

    // Getters and Setters =====================================================

    protected RelatorioVisitaRepository getRelatorioVisitaRepository() {
        if (relatorioVisitaRepository == null) {
            relatorioVisitaRepository = new RelatorioVisitaRepositoryHibernate();
        }

        return relatorioVisitaRepository;
    }

    protected Validator getRelatorioVisitaValidator() {
        if (relatorioVisitaValidator == null) {
            relatorioVisitaValidator = new Validator<RelatorioVisita>();
        }
        return relatorioVisitaValidator;
    }

    protected UsuarioService getUsuarioService() {
        if (usuarioService == null) {
            usuarioService = new UsuarioServiceImpl();
        }
        return usuarioService;
    }

    public AssociadoService getAssociadoService() {
        if (associadoService == null) {
            associadoService = new AssociadoServiceImpl();
        }
        return associadoService;
    }

    public TecnicoService getTecnicoService() {
        if (tecnicoService == null) {
            tecnicoService = new TecnicoServiceImpl();
        }
        return tecnicoService;
    }
}
