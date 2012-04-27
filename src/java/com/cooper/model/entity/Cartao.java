package com.cooper.model.entity;

import java.io.Serializable;

public class Cartao implements Serializable {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}