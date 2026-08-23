package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cobranca_adicional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CobrancaAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobranca")
    private Integer idCobranca;

    @Column(name = "descricao_cobranca")
    private String descricaoCobranca;

    @Column(name = "valor_cobranca")
    private BigDecimal valorCobranca;

    @ManyToOne
    @JoinColumn(name = "id_locacao_cobranca")
    private Locacao locacao;
}
