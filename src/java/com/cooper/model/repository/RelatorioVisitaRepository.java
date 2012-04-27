package com.cooper.model.repository;

import com.cooper.model.entity.RelatorioVisita;
import com.cooper.model.entity.Usuario;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface RelatorioVisitaRepository extends LookupRepository<RelatorioVisita, Integer>  {
    public List<RelatorioVisita> findAllByUsuario(Usuario usuario) throws RepositoryException;
    public List<RelatorioVisita> findAllByTecnico(Usuario usuario) throws RepositoryException;
}
