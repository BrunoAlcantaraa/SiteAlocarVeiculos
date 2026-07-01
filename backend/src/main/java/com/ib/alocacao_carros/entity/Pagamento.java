package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "valor_pagamento")
    private BigDecimal valorPagamento;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_locacao_pagamento")
    private Locacao locacaoPagamento;

    @ManyToOne
    @JoinColumn(name = "id_status_pagamento")
    private StatusPagamento statusPagamento;
}
