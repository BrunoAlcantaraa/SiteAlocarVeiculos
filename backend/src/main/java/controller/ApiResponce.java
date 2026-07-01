package controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponce<T> {
    private boolean sucesso;
    private String mensagem;
    private T dados;
    private LocalDateTime dataCriacao;
}
