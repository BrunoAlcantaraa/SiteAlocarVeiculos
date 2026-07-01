package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LocacaoConclusaoDTO {
    private Long id;
    private LocalDate DataRetorno;
    private Float kms;
}
