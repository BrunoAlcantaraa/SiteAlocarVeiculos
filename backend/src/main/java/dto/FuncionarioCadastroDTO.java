package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioCadastroDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String telefone;
    private BigDecimal salario;
    private String cargo;
}
