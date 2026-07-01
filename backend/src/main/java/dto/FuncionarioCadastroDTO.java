package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioCadastroDTO {
    private Long idFunc;
    private BigDecimal salario;
    private String cargo;
    private String username;
    private String senha;

    private PessoaCadastroDTO pessoa;
    private EnderecoCadastroDTO endereco;
}
