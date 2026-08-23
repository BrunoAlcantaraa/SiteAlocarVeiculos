package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_locacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatusLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statuslocacao")
    private Integer idStatusLocacao;

    @Column(name = "nome_statuslocacao")
    private String nomeStatusLocacao;
}
