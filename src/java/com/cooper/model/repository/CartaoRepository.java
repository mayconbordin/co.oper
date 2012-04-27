package com.cooper.model.repository;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Cartao;


public interface CartaoRepository {
    public Cartao getSaldoCartao(Associado associado);
}
