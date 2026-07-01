package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class FuncionarioEdicaoDTO {
    private Long idFuncLogado;
    private Long idFunc;
    private BigDecimal salario;
    private String cargo;
    private String username;
    private String senha;

    private PessoaEdicaoDTO pessoa;
    private EnderecoCadastroDTO endereco;
    private TelefoneEdicaoDTO telefone;
}
