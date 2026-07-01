package com.ib.alocacao_carros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCadastroDTO {
    PessoaCadastroDTO pessoa;
    EnderecoCadastroDTO endereco;
}
