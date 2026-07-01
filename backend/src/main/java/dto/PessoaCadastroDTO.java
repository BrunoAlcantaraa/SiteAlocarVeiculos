package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PessoaCadastroDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String telefone;
}
