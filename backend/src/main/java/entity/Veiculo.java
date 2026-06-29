package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    private String renavam;
    private String placa;
    private String cor;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "kms_atual")
    private Float kmsAtual;

    @Column(name = "tipo_veiculo")
    private String tipoVeiculo;

    @ManyToOne
    @JoinColumn(name = "id_modelo_veiculo")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_combustivel_veiculo")
    private Combustivel combustivel;

    @ManyToOne
    @JoinColumn(name = "id_status_veiculo")
    private StatusVeiculo statusVeiculo;
}
