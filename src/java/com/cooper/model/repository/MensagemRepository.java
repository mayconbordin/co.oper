package com.cooper.model.repository;

import com.cooper.model.entity.Mensagem;
import com.cooper.model.entity.Usuario;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface MensagemRepository extends LookupRepository<Mensagem, Integer>  {
    public List<Mensagem> findAllByUsuario(Usuario usuario, String filtroTipo, int resultSize) throws RepositoryException;
    public int countNaoLidasByUsuario(Usuario usuario) throws RepositoryException;

    public List<Mensagem> findAllByFuncionario(Usuario usuario, String filtroTipo, int resultSize) throws RepositoryException;
    public int countNaoLidasByFuncionario(Usuario usuario) throws RepositoryException;
}
