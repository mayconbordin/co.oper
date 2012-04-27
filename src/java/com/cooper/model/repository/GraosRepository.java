package com.cooper.model.repository;

import com.cooper.model.entity.Graos;
import com.cooper.model.entity.Usuario;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface GraosRepository extends LookupRepository<Graos, Integer>  {
    public List<Graos> findAllByUsuario(Usuario usuario) throws RepositoryException;
}
