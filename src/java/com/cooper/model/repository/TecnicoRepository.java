package com.cooper.model.repository;

import com.cooper.model.entity.Tecnico;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.math.BigDecimal;

public interface TecnicoRepository extends LookupRepository<Tecnico, String>  {
    public BigDecimal getNextSequence() throws RepositoryException;
}
