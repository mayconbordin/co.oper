package com.cooper.model.repository;

import com.cooper.model.entity.Acesso;
import com.cooper.model.entity.AcessoPk;
import com.strutstool.repository.LookupRepository;
import com.strutstool.repository.RepositoryException;
import java.util.Date;
import java.util.List;

public interface AcessoRepository extends LookupRepository<Acesso, AcessoPk>  {
    public List<Object[]> getAcessosBetweenDates(Date startDate, Date endDate) throws RepositoryException;
    public Integer getTotalAcessosByDate(Date start, Date end) throws RepositoryException;
}
