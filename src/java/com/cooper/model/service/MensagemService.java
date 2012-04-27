package com.cooper.model.service;

import com.cooper.model.entity.Mensagem;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;
import java.util.Map;

public interface MensagemService {
    public Mensagem addMensagemByAssociado(String conteudo, String usuarioId, int nivelUsuario) throws RepositoryException, ValidatorException;
    public Mensagem addMensagemByFuncionario(String conteudo, String funcionarioId, String associadoId, int nivelUsuario) throws RepositoryException, ValidatorException;
    
    public List<Mensagem> findAllByUsuario(String id, int resultSize) throws RepositoryException;
    public List<Mensagem> findAllByUsuario(String id) throws RepositoryException;
    public List<Mensagem> findAllEnviadasByUsuario(String id) throws RepositoryException;
    public List<Mensagem> findAllRecebidasByUsuario(String id) throws RepositoryException;

    public List<Mensagem> findAllByFuncionario(String id, int resultSize) throws RepositoryException;
    public List<Mensagem> findAllByFuncionario(String id) throws RepositoryException;
    public List<Mensagem> findAllEnviadasByFuncionario(String id) throws RepositoryException;
    public List<Mensagem> findAllRecebidasByFuncionario(String id) throws RepositoryException;
    
    public void marcarComoLida(Integer id) throws RepositoryException;
    public int countNaoLidasByUsuario(String id) throws RepositoryException;
    public int countNaoLidasByFuncionario(String id) throws RepositoryException;
}
