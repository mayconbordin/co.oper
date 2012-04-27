package com.cooper.model.repository;

import com.cooper.model.entity.Titulo;
import com.cooper.model.entity.Usuario;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface TituloRepository extends LookupRepository<Titulo, Integer>  {
    public List<Titulo> findAllByUsuario(Usuario usuario) throws RepositoryException;
    public double getTotalTitulosByUsuario(Usuario usuario) throws RepositoryException;
}
