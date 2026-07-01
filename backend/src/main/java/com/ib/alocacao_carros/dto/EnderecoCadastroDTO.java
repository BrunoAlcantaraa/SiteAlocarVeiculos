package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoCadastroDTO {
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String cep;
    private String complemento;
}
