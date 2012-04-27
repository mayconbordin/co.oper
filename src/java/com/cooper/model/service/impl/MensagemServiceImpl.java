package com.cooper.model.service.impl;

import com.cooper.model.entity.Associado;
import com.cooper.model.repository.MensagemRepository;
import com.cooper.model.repository.hibernate.MensagemRepositoryHibernate;
import com.cooper.model.entity.Mensagem;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.MensagemService;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.Calendar;
import java.util.List;

public class MensagemServiceImpl implements MensagemService {
    private MensagemRepository mensagemRepository;
    private UsuarioService usuarioService;
    private AssociadoService associadoService;
    private Validator mensagemValidator;

    public MensagemServiceImpl() {}

    public MensagemServiceImpl(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }
    
    // Implemented interface methods ===========================================

    public Mensagem addMensagemByAssociado(String conteudo, String usuarioId, int nivelUsuario) throws RepositoryException, ValidatorException {
        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(conteudo);
        mensagem.setData(Calendar.getInstance().getTime());
        mensagem.setFonteLeu(true);
        mensagem.setEnviadoPor(usuarioId);
        mensagem.setEnviadoPorFonte(nivelUsuario);

        getMensagemValidator().validate(mensagem);
        getMensagemRepository().save(mensagem);
        return mensagem;
    }

    public Mensagem addMensagemByFuncionario(String conteudo, String funcionarioId, String associadoId, int nivelUsuario) throws RepositoryException, ValidatorException {
        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(conteudo);
        mensagem.setData(Calendar.getInstance().getTime());
        mensagem.setFonteLeu(true);
        mensagem.setEnviadoPor(funcionarioId);
        mensagem.setEnviadoPorFonte(nivelUsuario);
        mensagem.setEnviadoPara(associadoId);

        Associado associado = getAssociadoService().get(associadoId);
        if (associado == null) {
            throw new RepositoryException("Associado não existe");
        }

        mensagem.setAssociado(associado);

        getMensagemValidator().validate(mensagem);
        getMensagemRepository().save(mensagem);
        return mensagem;
    }

    public List<Mensagem> findAllByUsuario(String id, int resultSize) throws RepositoryException {
        return findAllByUsuario(id, "todas", resultSize);
    }

    public List<Mensagem> findAllByUsuario(String id) throws RepositoryException {
        return findAllByUsuario(id, "todas", -1);
    }

    public List<Mensagem> findAllEnviadasByUsuario(String id) throws RepositoryException {
        return findAllByUsuario(id, "enviadas", -1);
    }

    public List<Mensagem> findAllRecebidasByUsuario(String id) throws RepositoryException {
        return findAllByUsuario(id, "recebidas", -1);
    }

    public void marcarComoLida(Integer id) throws RepositoryException {
        Mensagem mensagem = getMensagemRepository().get(id);
        mensagem.setDestinoLeu(true);
        getMensagemRepository().save(mensagem);
    }
    
    public int countNaoLidasByUsuario(String id) throws RepositoryException {
        Usuario usuario = getUsuarioService().get(id);
        return getMensagemRepository().countNaoLidasByUsuario(usuario);
    }

    public List<Mensagem> findAllByFuncionario(String id) throws RepositoryException {
        return findAllByFuncionario(id, "todas", -1);
    }

    public List<Mensagem> findAllByFuncionario(String id, int resultSize) throws RepositoryException {
        return findAllByFuncionario(id, "todas", resultSize);
    }

    public List<Mensagem> findAllEnviadasByFuncionario(String id) throws RepositoryException {
        return findAllByFuncionario(id, "enviadas", -1);
    }

    public List<Mensagem> findAllRecebidasByFuncionario(String id) throws RepositoryException {
        return findAllByFuncionario(id, "recebidas", -1);
    }

    public int countNaoLidasByFuncionario(String id) throws RepositoryException {
        Usuario usuario = new Usuario(id);
        return getMensagemRepository().countNaoLidasByFuncionario(usuario);
    }

    // Internal functions ======================================================

    private List<Mensagem> findAllByFuncionario(String id, String filtroTipo, int resultSize) throws RepositoryException {
        Usuario usuario = getUsuarioService().load(id);
        List<Mensagem> list = getMensagemRepository().findAllByFuncionario(usuario, filtroTipo, resultSize);

        for (Mensagem msg : list) {
            String codUsuario = (msg.getEnviadoPorFonte() == Usuario.NIVEL_FUNCIONARIO)
                    ? msg.getEnviadoPara() : msg.getEnviadoPor();

            Associado assoc = null;
            if (codUsuario == null || codUsuario.isEmpty()) {
                assoc = new Associado();
                assoc.setNome("anônimo");
            } else {
                assoc = getAssociadoService().get(codUsuario);
            }
            
            msg.setAssociado(assoc);
        }

        return list;
    }

    private List<Mensagem> findAllByUsuario(String id, String filtroTipo, int resultSize) throws RepositoryException {
        Usuario usuario = new Usuario(id);
        List<Mensagem> list = getMensagemRepository().findAllByUsuario(usuario, filtroTipo, resultSize);

        for (Mensagem msg : list) {
            String codUsuario = (msg.getEnviadoPorFonte() == Usuario.NIVEL_FUNCIONARIO)
                    ? msg.getEnviadoPara() : msg.getEnviadoPor();

            Associado assoc = null;
            if (codUsuario == null || codUsuario.isEmpty()) {
                assoc = new Associado();
                assoc.setNome("anônimo");
            } else {
                assoc = getAssociadoService().get(codUsuario);
            }

            msg.setAssociado(assoc);
        }

        return list;
    }

    // Getters and Setters =====================================================

    protected MensagemRepository getMensagemRepository() {
        if (mensagemRepository == null) {
            mensagemRepository = new MensagemRepositoryHibernate();
        }

        return mensagemRepository;
    }

    protected Validator getMensagemValidator() {
        if (mensagemValidator == null) {
            mensagemValidator = new Validator<Mensagem>();
        }
        return mensagemValidator;
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
}
