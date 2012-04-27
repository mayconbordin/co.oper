package com.cooper.model.repository.webservice;

import com.cooper.model.entity.Associado;
import com.cooper.model.entity.Cartao;
import com.cooper.model.repository.CartaoRepository;

public class CartaoRepositoryWebService implements CartaoRepository {

    public Cartao getSaldoCartao(Associado associado) {
        Cartao cartao = new Cartao();
        cartao.setSaldo(500.0);

        return cartao;
    }
    
}
