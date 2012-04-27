package com.cooper.funcionario.controller;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Mensagem;
import com.cooper.model.entity.Usuario;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.MensagemService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public class MensagemController extends StrutsController {
    private MensagemService mensagemService;
    private AssociadoService associadoService;

    private List<Mensagem> mensagemList;
    private List<Associado> associadoList;

    private String conteudo;
    private String paraAssociadoId;
    private Integer mensagemId;
    private String funcionarioId;

    private String list = "todas";
    
    public MensagemController() {
        funcionarioId = getUserAuth().getName();
    }

    // Actions =================================================================

    public String index() {
        try {
            if (list.toLowerCase().equals("recebidas")) {
                mensagemList = mensagemService.findAllRecebidasByFuncionario(getUserAuth().getName());
            } else if (list.toLowerCase().equals("enviadas")) {
                mensagemList = mensagemService.findAllEnviadasByFuncionario(getUserAuth().getName());
            } else {
                mensagemList = mensagemService.findAllByFuncionario(getUserAuth().getName());
            }

            associadoList = associadoService.getAll();
        } catch (RepositoryException ex) {
            errorHandler(ex);
        }
        return SUCCESS;
    }

    public String add() {
        try {
            Mensagem mensagem = getMensagemService()
                    .addMensagemByFuncionario(
                        conteudo,
                        getUserAuth().getName(),
                        paraAssociadoId,
                        Usuario.NIVEL_FUNCIONARIO
                    );
            request.setAttribute("mensagem", mensagem);
        } catch (RepositoryException ex) {
            errorHandler(ex);
        } catch (ValidatorException ex) {
            errorHandler(ex);
        }

        return SUCCESS;
    }

    public String listByFuncionario() {
        try {
            mensagemList = getMensagemService().findAllByFuncionario(getUserAuth().getName(), 8);
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

    public String countNaoLidasByFuncionario() {
        try {
            int count = getMensagemService().countNaoLidasByFuncionario(getUserAuth().getName());
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

    public String getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Integer mensagemId) {
        this.mensagemId = mensagemId;
    }

    public AssociadoService getAssociadoService() {
        return associadoService;
    }

    public void setAssociadoService(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    public String getParaAssociadoId() {
        return paraAssociadoId;
    }

    public void setParaAssociadoId(String paraAssociadoId) {
        this.paraAssociadoId = paraAssociadoId;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public List<Associado> getAssociadoList() {
        return associadoList;
    }

    public void setAssociadoList(List<Associado> associadoList) {
        this.associadoList = associadoList;
    }
}
