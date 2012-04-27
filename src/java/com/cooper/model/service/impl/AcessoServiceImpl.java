package com.cooper.model.service.impl;

import com.cooper.model.entity.Acesso;
import com.cooper.model.repository.AcessoRepository;
import com.cooper.model.repository.hibernate.AcessoRepositoryHibernate;
import com.cooper.model.service.AcessoService;
import com.strutstool.repository.RepositoryException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;

public class AcessoServiceImpl implements AcessoService {
    private AcessoRepository acessoRepository;
    
    // Implemented interface methods ===========================================
    public void newAcesso(String ip, String pagina, String codAssociado) throws RepositoryException {
        Acesso acesso = new Acesso(
                Calendar.getInstance().getTime(),
                ip,
                pagina,
                codAssociado);
        getAcessoRepository().save(acesso);
    }

    public Map<Date, Integer> getAcessosBetweenDates(Date start, Date end) throws RepositoryException {
        List<Object[]> list = getAcessoRepository().getAcessosBetweenDates(start, end);

        Map<Date, Integer> acessos = new TreeMap<Date, Integer>();
        
        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);

        while(true) {
            acessos.put(startDate.toDate(), 0);
            startDate = startDate.plusDays(1);

            if (startDate.toLocalDate().compareTo(endDate.toLocalDate()) == 0) {
                break;
            }
        }

        for (Object[] obj : list) {
            Date data = new DateTime((Date) obj[0]).toDate();
            acessos.put(data, (Integer) obj[1]);
        }

        return acessos;
    }

    public Integer getTotalAcessosHoje() throws RepositoryException {
        Date date = Calendar.getInstance().getTime();

        MutableDateTime start = new MutableDateTime(date);
        start.setTime(0, 0, 0, 0);

        MutableDateTime end = new MutableDateTime(date);
        end.setTime(23, 59, 59, 0);

        return getAcessoRepository().getTotalAcessosByDate(start.toDate(), end.toDate());
    }

    // Getters and Setters =====================================================
    public AcessoRepository getAcessoRepository() {
        if (acessoRepository == null) {
            acessoRepository = new AcessoRepositoryHibernate();
        }
        return acessoRepository;
    }
}
