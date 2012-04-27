package com.cooper.model.service;

import com.cooper.model.entity.Associado;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public interface AssociadoService {
    public Associado get(String id) throws RepositoryException;
    public Associado load(String id) throws RepositoryException;
    public List<Associado> getAll() throws RepositoryException;
    public void criarConta(String codigo, String email, String pass) throws RepositoryException, ValidatorException;
    public boolean associadoWithCpfCnpjExists(String codigo, String cpfCnpj) throws RepositoryException;
    public void setTemConta(String codigo) throws RepositoryException;
}
