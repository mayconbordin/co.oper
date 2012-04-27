package com.cooper.model.service.impl;

import com.cooper.model.entity.Associado;
import com.cooper.model.repository.OrcamentoRepository;
import com.cooper.model.repository.hibernate.OrcamentoRepositoryHibernate;
import com.cooper.model.entity.Orcamento;
import com.cooper.model.entity.Produto;
import com.cooper.model.entity.ProdutoDoOrcamento;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.OrcamentoService;
import com.cooper.model.service.ProdutoDoOrcamentoService;
import com.cooper.model.service.ProdutoService;
import com.cooper.model.service.UsuarioService;
import com.cooper.model.utils.ProdutoDoOrcamentoArrayList;
import com.cooper.model.utils.ProdutoDoOrcamentoList;
import com.strutstool.repository.RepositoryException;
import com.strutstool.session.Session;
import com.strutstool.validator.Validator;
import com.strutstool.validator.ValidatorException;
import java.util.Calendar;
import java.util.List;

public class OrcamentoServiceImpl implements OrcamentoService {
    public static final String ORCAMENTO_SESSION = "orcamento";
    
    private OrcamentoRepository orcamentoRepository;
    private Validator orcamentoValidator;
    private Session session;

    private ProdutoService produtoService;
    private AssociadoService associadoService;
    private UsuarioService usuarioService;
    private ProdutoDoOrcamentoService produtoDoOrcamentoService;
    private ProdutoDoOrcamentoList produtos;

    public OrcamentoServiceImpl() {}

    public OrcamentoServiceImpl(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    // Implemented interface methods ===========================================

    public Orcamento get(Integer id) throws RepositoryException {
        return getOrcamentoRepository().get(id);
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    // Main functionality

    public ProdutoDoOrcamentoList getProdutos() {
        produtos = (ProdutoDoOrcamentoList) session.getAttribute(ORCAMENTO_SESSION);
        return (produtos == null) ? new ProdutoDoOrcamentoArrayList() : produtos;
    }

    public int getProdutosCount() {
        produtos = (ProdutoDoOrcamentoList) session.getAttribute(ORCAMENTO_SESSION);
        return (produtos == null) ? 0 : produtos.size();
    }

    public void addProduto(int produtoId, int qtde) throws RepositoryException {
        produtos = (ProdutoDoOrcamentoList) session.getAttribute(ORCAMENTO_SESSION);
        if (produtos == null) {
            produtos = new ProdutoDoOrcamentoArrayList();
            session.setAttribute(ORCAMENTO_SESSION, produtos);
        }

        Produto produto = getProdutoService().load(produtoId);
        ProdutoDoOrcamento pdo = new ProdutoDoOrcamento(produto, qtde);

        if (!produtos.containsProduto(produto.getCodigo())) {
            produtos.add(pdo);
        }
    }

    public void removeProduto(int produtoId) {
        produtos = (ProdutoDoOrcamentoList) session.getAttribute(ORCAMENTO_SESSION);

        if (produtos != null) {
            produtos.removeWithProduto(produtoId);
        }
    }

    public void saveOrcamento(String usuarioId) throws RepositoryException, ValidatorException {
        Associado associado = getAssociadoService().load(usuarioId);

        Orcamento orcamento = new Orcamento();
        orcamento.setAssociado(associado);
        orcamento.setStatus(Orcamento.STATUS_PENDENTE);
        orcamento.setData(Calendar.getInstance().getTime());
        getOrcamentoRepository().save(orcamento);

        produtos = (ProdutoDoOrcamentoList) session.getAttribute(ORCAMENTO_SESSION);
        for (ProdutoDoOrcamento pdo : produtos) {
            pdo.getPrimaryKey().setOrcamento(orcamento);
        }
        getProdutoDoOrcamentoService().saveAll(produtos);

        session.removeAttribute(ORCAMENTO_SESSION);
    }

    public void saveRespostaOrcamento(int orcamentoId, String comentario, double total) throws RepositoryException, ValidatorException {
        Orcamento orcamento = getOrcamentoRepository().get(orcamentoId);
        orcamento.setComentario(comentario);
        orcamento.setTotal(total);
        orcamento.setStatus(Orcamento.STATUS_RESPONDIDO);

        getOrcamentoValidator().validate(orcamento);
        getOrcamentoRepository().save(orcamento);
    }

    public void removeOrcamento() {
        session.removeAttribute(ORCAMENTO_SESSION);
    }

    public List<Orcamento> findAllByUsuario(String id) throws RepositoryException {
        Usuario usuario = getUsuarioService().get(id);
        return getOrcamentoRepository().findAllByUsuario(usuario);
    }

    public List<Orcamento> findAllOrderByStatusAndData() throws RepositoryException {
        String[] orderBy = {"status", "data"};
        String[] orderType = {"asc", "desc"};
        return getOrcamentoRepository().findAll(orderBy, orderType);
    }

    public Integer getTotalOrcamentosPendentes() throws RepositoryException {
        return getOrcamentoRepository().getTotalOrcamentosPendentes();
    }

    // Getters and Setters =====================================================

    protected OrcamentoRepository getOrcamentoRepository() {
        if (orcamentoRepository == null) {
            orcamentoRepository = new OrcamentoRepositoryHibernate();
        }

        return orcamentoRepository;
    }

    protected Validator getOrcamentoValidator() {
        if (orcamentoValidator == null) {
            orcamentoValidator = new Validator<Orcamento>();
        }
        return orcamentoValidator;
    }

    protected ProdutoService getProdutoService() {
        if (produtoService == null) {
            produtoService = new ProdutoServiceImpl();
        }
        return produtoService;
    }

    protected AssociadoService getAssociadoService() {
        if (associadoService == null) {
            associadoService = new AssociadoServiceImpl();
        }
        return associadoService;
    }

    protected ProdutoDoOrcamentoService getProdutoDoOrcamentoService() {
        if (produtoDoOrcamentoService == null) {
            produtoDoOrcamentoService = new ProdutoDoOrcamentoServiceImpl();
        }
        return produtoDoOrcamentoService;
    }

    protected UsuarioService getUsuarioService() {
        if (usuarioService == null) {
            usuarioService = new UsuarioServiceImpl();
        }
        return usuarioService;
    }
}
