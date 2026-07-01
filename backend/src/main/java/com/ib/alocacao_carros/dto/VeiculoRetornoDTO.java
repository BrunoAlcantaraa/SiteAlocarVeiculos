package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRetornoDTO {
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private String renavam;
    private String placa;
    private String cor;
    private String urlImagem;
    private Float kmAtual;
    private String tipo;
    private String combustivel;
}
