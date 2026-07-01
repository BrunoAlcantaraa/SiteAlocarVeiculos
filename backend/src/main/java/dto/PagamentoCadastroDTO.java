package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagamentoCadastroDTO {
    private String formaPagamento;
    private Long idLocacao;
}
