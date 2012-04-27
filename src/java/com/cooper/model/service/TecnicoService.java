package com.cooper.model.service;

import com.cooper.model.entity.Tecnico;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public interface TecnicoService {
    public List<Tecnico> getAll() throws RepositoryException;
    public Tecnico get(String id) throws RepositoryException;
    public Tecnico newTecnico(String nome, String senha, String email) throws RepositoryException, ValidatorException;
}
