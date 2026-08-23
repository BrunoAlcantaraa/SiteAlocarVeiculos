package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LocacaoEdicaoDTO {
    private Integer id;
    private Integer idFunc;
    private LocalDate dataSaida;
    private LocalDate dataRetornoPrevista;
    private Float kms;
    private String placaVeiculo;
}
