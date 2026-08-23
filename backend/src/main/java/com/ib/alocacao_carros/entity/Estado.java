package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estd")
    private Integer idEstado;

    @Column(name = "nome_estd") //como eu vou colocar um nome diferente na variavel, tem que referenciar a coluna
    private String nomeEstado;

    private String sigla;
}
