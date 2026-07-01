package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PagamentoRetornoDTO {
    private String formaPagamento;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String status;

    private LocacaoRetornoDTO locacao;
}
