package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FuncionarioCadastroDTO {
    private Integer idFunc;
    private BigDecimal salario;
    private String cargo;
    private String username;
    private String senha;

    private PessoaCadastroDTO pessoa;
    private EnderecoCadastroDTO endereco;
}
