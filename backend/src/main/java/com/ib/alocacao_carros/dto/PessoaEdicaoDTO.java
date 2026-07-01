package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaEdicaoDTO {
    private String nome;
    private String email;
    private String sexo;
    private LocalDate dataNascimento;
    private String telefone;
}
