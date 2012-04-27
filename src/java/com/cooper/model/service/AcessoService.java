package com.cooper.model.service;

import com.strutstool.repository.RepositoryException;
import java.util.Date;
import java.util.Map;

public interface AcessoService {
    public void newAcesso(String ip, String pagina, String codAssociado) throws RepositoryException;
    public Map<Date, Integer> getAcessosBetweenDates(Date start, Date end) throws RepositoryException;
    public Integer getTotalAcessosHoje() throws RepositoryException;
}
