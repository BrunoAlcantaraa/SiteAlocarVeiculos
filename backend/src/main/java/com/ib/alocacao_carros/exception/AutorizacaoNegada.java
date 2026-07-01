package com.ib.alocacao_carros.exception;

public class AutorizacaoNegada extends RuntimeException {
    public AutorizacaoNegada(String message) {
        super(message);
    }
}
