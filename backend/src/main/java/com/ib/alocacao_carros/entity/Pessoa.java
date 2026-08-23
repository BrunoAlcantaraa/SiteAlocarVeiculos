package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pes")
    private Integer idPessoa;

    @Column(name = "nome_pes")
    private String nomePessoa;

    private String CPF;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private Boolean sexo;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_endr_pes")
    private Endereco endereco;

    @OneToOne(mappedBy = "pessoa")
    private Cliente cliente;
}
