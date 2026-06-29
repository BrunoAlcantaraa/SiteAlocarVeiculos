package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_func")
    private Long idFuncionario;

    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "id_pes_func")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_cargo_func")
    private Cargo cargo;
}
