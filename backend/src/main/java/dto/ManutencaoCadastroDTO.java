package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ManutencaoCadastroDTO {
    private LocalDate dataInicio;
    private String descricao;
    private String placaVeiculo;
}
