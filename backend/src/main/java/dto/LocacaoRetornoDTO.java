package dto;

import entity.CobrancaAdicional;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LocacaoRetornoDTO {
    private LocalDate dataSaida;
    private LocalDate dataRetornoPrevista;
    private LocalDate DataRetornoReal;
    private Float kmSaida;
    private Float kmRetorno;
    private String status;
    private List<CobrancaAdicional> cobrancas;

    private ClienteRetornoDTO cliente;
    private FuncionarioRetornoDTO funcionario;
    private VeiculoRetornoDTO veiculo;
}
