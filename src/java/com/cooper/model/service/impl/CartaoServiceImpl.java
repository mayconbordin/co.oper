package com.cooper.model.service.impl;

import com.cooper.model.entity.Associado;
import com.cooper.model.repository.CartaoRepository;
import com.cooper.model.repository.webservice.CartaoRepositoryWebService;
import com.cooper.model.service.AssociadoService;
import com.cooper.model.service.CartaoService;
import com.strutstool.repository.RepositoryException;

public class CartaoServiceImpl implements CartaoService {
    private CartaoRepository cartaoRepository;
    private AssociadoService associadoService;

    public CartaoServiceImpl() {}

    public CartaoServiceImpl(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }
    
    // Implemented interface methods ===========================================

    public double getSaldo(String associadoId) {
        try {
            Associado associado = getAssociadoService().get(associadoId);
            return getCartaoRepository().getSaldoCartao(associado).getSaldo();
        } catch (RepositoryException ex) {
            return 0.0;
        }
    }

    // Getters and Setters =====================================================

    protected CartaoRepository getCartaoRepository() {
        if (cartaoRepository == null) {
            cartaoRepository = new CartaoRepositoryWebService();
        }

        return cartaoRepository;
    }

    protected AssociadoService getAssociadoService() {
        if (associadoService == null) {
            associadoService = new AssociadoServiceImpl();
        }
        return associadoService;
    }
}
