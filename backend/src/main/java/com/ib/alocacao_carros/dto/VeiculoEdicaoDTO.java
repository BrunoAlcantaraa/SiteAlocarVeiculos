package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoEdicaoDTO {
    private Integer idFunc;
    private Integer idVeiculo;
    private String placa;
    private String cor;
    private String urlImagem;
    private Float kmAtual;
    private String tipo;
    private String status;
}
