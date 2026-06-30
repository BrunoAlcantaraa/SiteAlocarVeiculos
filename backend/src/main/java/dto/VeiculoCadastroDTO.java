package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoCadastroDTO {
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private String renavam;
    private String placa;
    private String cor;
    //É NECESSÁRIO PENSAR EM COMO SERÁ FEITO COM A URL DA IMAGEM
    private Float kmAtual;
    private String tipo;
    private String combustivel;
}
