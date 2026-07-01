package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteEdicaoDTO {
    Long id;
    PessoaEdicaoDTO pessoa;
    EnderecoCadastroDTO endereco;
    TelefoneEdicaoDTO telefone;
}
