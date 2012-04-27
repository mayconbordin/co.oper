package com.cooper.associado.controller;

import com.cooper.model.service.CartaoService;
import com.strutstool.struts.StrutsController;

public class CartaoController extends StrutsController {
    private CartaoService cartaoService;
    private double saldo;

    // Actions =================================================================
    public String mostraSaldo() {
        saldo = getCartaoService().getSaldo(getUserAuth().getName());
        return SUCCESS;
    }

    // Getters and Setters =====================================================
    
    public CartaoService getCartaoService() {
        return cartaoService;
    }

    public void setCartaoService(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
