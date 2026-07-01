package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LocacaoCadastroDTO {
    private LocalDate dataSaida;
    private LocalDate dataRetornoPrevista;
    private Float kmSaida;
    private String cpfCliente;
    private String cpfFuncionario;
    private String placaVeiculo;
}
