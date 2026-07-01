package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CobrancaPlusCadastro {
    private String descricao;
    private BigDecimal valor;
    private Long idLocacao;
}
