package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "manutencao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manut")
    private Long idManutencao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "descricao_manut")
    private String descricaoManutencao;

    @Column(name = "valor_manut")
    private BigDecimal valorManutencao;

    @ManyToOne
    @JoinColumn(name = "id_veiculo_manut")
    private Veiculo veiculo;
}
