package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Long idModelo;

    @Column(name = "nome_modelo")
    private String nomeModelo;

    @Column(name = "ano_fab")
    private Integer anoFab;

    @Column(name = "valor_diario")
    private BigDecimal valorDiario;

    @ManyToOne
    @JoinColumn(name = "id_marca_modelo")
    private Marca marca;
}
