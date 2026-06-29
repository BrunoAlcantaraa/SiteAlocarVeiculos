package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "locacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacao")
    private Long idLocacao;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "data_retorno_previsto")
    private LocalDate dataRetornoPrevisto;

    @Column(name = "data_retorno_real")
    private LocalDate dataRetornoReal;

    @Column(name = "kms_saida")
    private Float kmsSaida;

    @Column(name = "kms_retorno")
    private Float kmsRetorno;

    @ManyToOne
    @JoinColumn(name = "id_cliente_locacao")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_veiculo_locacao")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_func_locacao")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_status_locacao")
    private StatusLocacao statusLocacao;
}
