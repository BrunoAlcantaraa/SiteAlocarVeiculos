package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tel")
    private Long idTel;

    @Column(name = "numero_tel")
    private String numeroTel;

    @ManyToOne
    @JoinColumn(name = "id_pes_tel")
    private Pessoa pessoa;
}
