package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PagamentoCadastroPosteriorDTO {
    private String formaPagamento;
    private Integer idLocacao;
    private LocalDate dataPagamento;
}
