package com.cooper.model.service;

import com.cooper.model.entity.Titulo;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface TituloService {
    public List<Titulo> findAllByUsuario(String id) throws RepositoryException;
    public double getTotalTitulosByUsuario(String id) throws RepositoryException;
}
