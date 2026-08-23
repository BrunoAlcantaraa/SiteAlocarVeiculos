package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class FuncionarioEdicaoDTO {
    private Integer idFuncLogado;
    private Integer idFunc;
    private BigDecimal salario;
    private String cargo;
    private String username;
    private String senha;

    private PessoaEdicaoDTO pessoa;
    private EnderecoCadastroDTO endereco;
    private TelefoneEdicaoDTO telefone;
}
