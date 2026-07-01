package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endr")
    private Integer idEndr;

    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String CEP;

    @ManyToOne
    @JoinColumn(name = "id_cid_endr")
    private Cidade cidade;
}
