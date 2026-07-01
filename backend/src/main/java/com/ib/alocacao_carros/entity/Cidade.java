package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cid")
    private Long idCidade;

    @Column(name = "nome_cid")
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "id_estd_cid")
    private Estado estado;
}
