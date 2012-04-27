package com.cooper.model.service;

import com.cooper.model.entity.Usuario;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;

public interface UsuarioService {
    public void updateUsuario(String usuarioId, String email, String senha) throws RepositoryException, ValidatorException;
    public void updateUsuario(String usuarioId, String senha) throws RepositoryException, ValidatorException;
    public Usuario get(String id) throws RepositoryException;
    public Usuario load(String id) throws RepositoryException;
    public void addUsuario(String id, String email, String senha, int nivel) throws RepositoryException, ValidatorException;
    public boolean cadastrarAssociado(String id, String email, String senha, String cpfCnpj) throws RepositoryException, ValidatorException;
}
