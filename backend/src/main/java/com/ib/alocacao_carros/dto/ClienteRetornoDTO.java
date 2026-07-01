package com.ib.alocacao_carros.dto;

import com.ib.alocacao_carros.entity.Telefone;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteRetornoDTO {
    private PessoaRetornoDTO pessoaDTO;
    private EnderecoCadastroDTO enderecoDTO;
    private List<Telefone> Telefones;
}
