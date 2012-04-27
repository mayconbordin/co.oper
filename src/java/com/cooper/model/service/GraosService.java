package com.cooper.model.service;

import com.cooper.model.entity.Graos;
import com.strutstool.repository.RepositoryException;
import java.util.List;

public interface GraosService {
    public List<Graos> findAllByUsuario(String id) throws RepositoryException;
}
