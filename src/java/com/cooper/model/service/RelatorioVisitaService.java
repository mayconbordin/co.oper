package com.cooper.model.service;

import com.cooper.model.entity.RelatorioVisita;
import com.strutstool.repository.RepositoryException;
import com.strutstool.validator.ValidatorException;
import java.util.List;

public interface RelatorioVisitaService {
    public void saveAvaliacao(Integer relatorioVisitaId, String comentario, int nota) throws RepositoryException, ValidatorException;
    public RelatorioVisita saveRelatorioVisita(int id, String data, String descricao, String associadoId, String tecnicoId) throws RepositoryException, ValidatorException;
    public List<RelatorioVisita> findAllByUsuario(String id) throws RepositoryException;
    public List<RelatorioVisita> findAllByTecnico(String id) throws RepositoryException;
}
