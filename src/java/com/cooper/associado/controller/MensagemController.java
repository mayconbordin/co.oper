package com.cooper.associado.controller;

import com.cooper.model.entity.Usuario;
import java.util.List;
import com.cooper.model.entity.Mensagem;
import com.cooper.model.service.MensagemService;
import com.strutstool.struts.StrutsController;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;

public class MensagemController extends StrutsController {
    private List<Mensagem> mensagemList;

    private MensagemService mensagemService;

    private String conteudo;
    private Integer mensagemId;
    private String usuarioId;

    private String list = "todas";

    public MensagemController() {
        usuarioId = getUserAuth().getName();
    }

    // Actions =================================================================
    
    public String index() {
        try {
            if (list.toLowerCase().equals("recebidas")) {
                mensagemList = mensagemService.findAllRecebidasByUsuario(getUserAuth().getName());
            } else if (list.toLowerCase().equals("enviadas")) {
                mensagemList = mensagemService.findAllEnviadasByUsuario(getUserAuth().getName());
            } else {
                mensagemList = mensagemService.findAllByUsuario(getUserAuth().getName());
            }
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String add() {
        try {
            Mensagem mensagem = getMensagemService().addMensagemByAssociado(conteudo, getUserAuth().getName(), Usuario.NIVEL_ASSOCIADO);
            request.setAttribute("mensagem", mensagem);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String listByUsuario() {
        try {
            mensagemList = getMensagemService().findAllByUsuario(getUserAuth().getName(), 8);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public void marcarComoLida() {
        try {
            getMensagemService().marcarComoLida(mensagemId);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
    }

    public String countNaoLidasByUsuario() {
        try {
            int count = getMensagemService().countNaoLidasByUsuario(getUserAuth().getName());
            request.setAttribute("count", count);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    // Getters and Setters =====================================================
    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    public MensagemService getMensagemService() {
        return mensagemService;
    }

    public void setMensagemService(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    public Integer getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Integer mensagemId) {
        this.mensagemId = mensagemId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
