package com.ib.alocacao_carros.dto;

import com.ib.alocacao_carros.entity.Telefone;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class FuncionarioRetornoDTO {
    private BigDecimal salario;
    private String cargo;

    private PessoaRetornoDTO pessoa;
    private EnderecoCadastroDTO endereco;
    private List<Telefone> telefones;
}
