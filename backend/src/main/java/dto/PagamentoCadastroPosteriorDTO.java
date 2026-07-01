package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PagamentoCadastroPosteriorDTO {
    private String formaPagamento;
    private Long idLocacao;
    private LocalDate dataPagamento;
}
