package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.exception.AcaoInvalida;
import com.ib.alocacao_carros.exception.AutorizacaoNegada;
import com.ib.alocacao_carros.exception.DadosInvalidos;
import com.ib.alocacao_carros.exception.RecursoNaoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AcaoInvalida.class)
    public ResponseEntity<ApiResponce<Void>> tratarAcaoInvalida(
            AcaoInvalida e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponce<>(
                        false,
                        e.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(AutorizacaoNegada.class)
    public ResponseEntity<ApiResponce<Void>> tratarAutorizacaoNegada(
            AutorizacaoNegada e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiResponce<>(
                        false,
                        e.getMessage(),
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DadosInvalidos.class)
    public ResponseEntity<ApiResponce<Void>> tratarDadosInvalidos(
            DadosInvalidos e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponce<>(
                        false,
                        e.getMessage(),
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<ApiResponce<Void>> tratarRecursoNaoEncontrado(
            RecursoNaoEncontrado e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponce<>(
                        false,
                        e.getMessage(),
                        null,
                        LocalDateTime.now()
                )
        );
    }
}
