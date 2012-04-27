package com.cooper.model.repository;

import com.cooper.model.entity.Associado;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;

public interface AssociadoRepository extends LookupRepository<Associado, String>  {
    public boolean associadoWithCpfCnpjExists(Associado associado) throws RepositoryException;
}
