package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatusPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_pagamento")
    private Integer idStatusPagamento;

    @Column(name = "nome_status_pagamento")
    private String nomeStatusPagamento;
}
